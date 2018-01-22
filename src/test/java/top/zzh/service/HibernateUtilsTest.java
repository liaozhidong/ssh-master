package top.zzh.service;

import top.zzh.bean.Stu;
import org.junit.Test;
import top.zzh.common.HibernateUtils;

public class HibernateUtilsTest {
    @Test
    public void testColumnToProperty() {
        System.out.println(HibernateUtils.columnToProperty("is_active"));
        System.out.println(HibernateUtils.propertyToColumn("isCustomerInfoDelete"));
        Stu stu = new Stu();
        stu.setNum("1");
        System.out.println(HibernateUtils.buildCriteriaSQL("select s.id as id, s.num as num, s.real_name as realName," +
                " c.title as className from t_stu s, t_class c where 1 = 1 and s.class_id = c.id", stu) + " limit 1, 5");
    }
}
