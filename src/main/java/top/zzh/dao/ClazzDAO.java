package top.zzh.dao;

import top.zzh.bean.Clazz;
import top.zzh.common.Pager;
import java.util.*;

public interface ClazzDAO extends BaseDAO<Clazz>{

    Pager<Clazz> listPagerCriteria(Pager<Clazz> pager,Clazz clazz);
}
