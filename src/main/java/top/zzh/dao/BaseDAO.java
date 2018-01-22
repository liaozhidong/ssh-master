package top.zzh.dao;

import top.zzh.common.Pager;

import java.util.List;

/**
 * @version 1.0
 * @author 曾志湖
 * @param <T>
 */
public interface BaseDAO<T> {
    /**
     * 新增数据
     * @param t
     */
    void save(T t);

    /**
     * 删除数据
     * @param t
     */
    void remove(T t);

    /**
     * 通过id删除数据
     * @param id
     */
    void removeById(Long id);

    /**
     * 修改数据
     * @param t
     */
    void update(T t);

    /**
     * 通过id查询数据
     * @param id
     * @return
     */
    T getById(Long id);

    /**
     * 查询所有的数据
     * @return
     */
    List<T> listAll();

    /**
     * 分页查询数据
     * @param pager
     * @return
     */
    Pager<T> listPager(Pager<T> pager);

    /**
     * 查询总数
     * @return
     */
    Long count();

    /**
     * 分页查询数据并实现模糊查询
     * @param pager
     * @param t
     * @return
     */
    Pager<T> listPagerCriteria(Pager<T> pager, T t);

    /**
     * 分页查询数据
     * @param t
     * @return
     */
    Long countCriteria(T t);
}
