package top.zzh.service;

import top.zzh.common.Pager;
import java.util.List;
public interface BaseService<T> {

    void save(T t);
    void remove(T t);
    void removeById(Long id);
    void update(T t);

    T getById(Long id);
    List<T> listAll();
    Pager<T> listPager(int pageNo, int pageSize);

    Pager<T> listPagerCriteria(int pageNo, int pageSize, T t);
}
