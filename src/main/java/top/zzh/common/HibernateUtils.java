package top.zzh.common;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Session;
import org.hibernate.query.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.lang.reflect.Field;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class HibernateUtils {

    /**
     * 分页并进行模糊查询
     * @param session
     * @param pager
     * @param t
     * @param tClass
     * @param <T>
     * @return
     */
    public static <T> Pager<T> pager(Session session, Pager<T> pager, T t, Class<T> tClass) {
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder(); // 条件构造器
        CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(tClass); // 创建一个stu对象的条件查询器
        Root<T> root = criteriaQuery.from(tClass); // root表示提供查询值的对象
        criteriaQuery = HibernateUtils.rebuildRowsCriteriaQuery(criteriaQuery, criteriaBuilder, root, t);
        Query query = session.createQuery(criteriaQuery); // 封装成Hibernate的查询器
        query.setFirstResult(pager.getBeginIndex());
        query.setMaxResults(pager.getPageSize());
        pager.setRows(query.list());
        return pager;
    }

    /**
     * 计算数据多少
     * @param session
     * @param t
     * @param tClass
     * @param countField
     * @param <T>
     * @return
     */
    public static <T> Long count(Session session, T t, Class<T> tClass, String countField) {
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
        Root<T> root = criteriaQuery.from(tClass);
        if (countField == null) {
            countField = "id";
        }
        criteriaQuery.select(criteriaBuilder.count(root.get(countField)));
        criteriaQuery = HibernateUtils.rebuildCountCriteriaQuery(criteriaQuery, criteriaBuilder, root, t);
        return session.createQuery(criteriaQuery).uniqueResult();
    }

    /**
     * 如果需要在方法级别使用泛型，则必须在返回值的前面声明泛型
     * @param criteriaQuery
     * @param criteriaBuilder
     * @param root
     * @param t 是查询对象
     * @param <T>
     * @return
     */
    public static <T> CriteriaQuery<T> rebuildRowsCriteriaQuery(CriteriaQuery<T> criteriaQuery,
                                                                CriteriaBuilder criteriaBuilder,
                                                                Root<T> root,
                                                                T t) {
        return rebuildCriteriaQuery(criteriaQuery, criteriaBuilder, root, t);
    }

    public static <T> CriteriaQuery<Long> rebuildCountCriteriaQuery(CriteriaQuery<Long> criteriaQuery,
                                                                    CriteriaBuilder criteriaBuilder,
                                                                    Root<T> root,
                                                                    T t) {
        return rebuildCriteriaQuery(criteriaQuery, criteriaBuilder, root, t);
    }

    private static <T> CriteriaQuery rebuildCriteriaQuery(CriteriaQuery criteriaQuery,
                                                          CriteriaBuilder criteriaBuilder,
                                                          Root<T> root,
                                                          T t) {
        if (t != null) {
            Field[] fields = t.getClass().getDeclaredFields();
            List<Predicate> predicateList = new ArrayList<>();
            for (Field field : fields) {
                field.setAccessible(true);
                String fieldName = field.getName();
                try {
                    Object value = field.get(t);
                    if (value != null && value instanceof String) {
                        predicateList.add(criteriaBuilder.like(root.get(fieldName), "%" + (String) value + "%"));
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
            if (predicateList.size() > 0) {
                // 支持多个条件查询
                criteriaQuery.where(criteriaBuilder.and(predicateList.toArray(new Predicate[predicateList.size()])));
            }
        }
        return criteriaQuery;
    }

    /**
     * 把data里面的数据封装到指定的t对象中，并且返回T对象所组成的数组
     * @param data
     * @param tClass
     * @param <T>
     * @return
     */
    public static <T> List<T> buildBean(List<Object[]> data, String[] aliasArray, Class<T> tClass) {
        List<T> list = new ArrayList<>();
        try {
            for (Object[] objects : data) {
                T t = tClass.newInstance();
                for (int i = 0, len = objects.length; i < len; i++) {
                    // aliasArray[i]正好对应于javabean属性名称
                    String fieldName = aliasArray[i]; // i = 0 id
                    Field field = tClass.getDeclaredField(fieldName); // id
                    field.setAccessible(true);
                    if (objects[i] instanceof BigInteger) {
                        field.set(t, ((BigInteger)objects[i]).longValue());
                    } else {
                        field.set(t, objects[i]);
                    }
                }
                list.add(t);
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 传递进来的sql语句请加上where 1 = 1
     * @param sql
     * @param t
     * @param <T>
     * @return
     */
    public static <T> String buildCriteriaSQL(String sql, T t) {
        StringBuilder sb = new StringBuilder(sql);
        if (t != null) {
            Field[] fields = t.getClass().getDeclaredFields();
            try {
                for (Field field : fields) {
                    field.setAccessible(true);
                    String property = field.getName();
                    Object value = field.get(t);
                    if (value != null && value instanceof String) {
                        sb.append(" and ").append(propertyToColumn(property)).append(" like '%").append((String) value).append("%'");
                    }
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }

    public static String columnToProperty(String column) {
        StringBuilder property = new StringBuilder("");
        if (column.contains("_")) {
            String[] strArray = column.split("_");
            property.append(strArray[0]);
            for (int i = 1, len = strArray.length; i < len; i++) {
                property.append(StringUtils.capitalize(strArray[i]));
            }
        } else {
            property.append(column);
        }
        return property.toString();
    }

    public static String propertyToColumn(String property) {
        List<Character> characterList = stringToList(property);
        List<Integer> indexes = new ArrayList<>();
        for (int i = 0, len =characterList.size(); i < len; i++) {
            if (characterList.get(i) >= 65 && characterList.get(i) <= 90) {
                indexes.add(i);
            }
        }
        int count = 0; // 已经替换过多少次
        for (Integer index : indexes) {
            // 所有需要添加下划线的位置
            characterList.add(index + count, '_');
            characterList.set(index + count + 1, (char) (characterList.get(index + count + 1) + 32));
            count++;
        }
        return listToString(characterList);
    }

    private static List<Character> stringToList(String str) {
        char[] chars = str.toCharArray();
        List<Character> characters = new ArrayList<>();
        for (char c : chars) {
            characters.add(c);
        }
        return characters;
    }

    private static String listToString(List<Character> characters) {
        StringBuilder sb = new StringBuilder("");
        for (Character c : characters) {
            sb.append(c);
        }
        return sb.toString();
    }

}
