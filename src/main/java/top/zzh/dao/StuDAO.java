package top.zzh.dao;


import top.zzh.bean.Stu;
import top.zzh.common.Pager;
import top.zzh.vo.StuClassVO;
import java.util.*;

public interface StuDAO extends BaseDAO<Stu>{

    Pager<StuClassVO> listAllWithClass(Pager<StuClassVO> pager, Stu stu);
    void saveClasses(Long stuId, List<Long> contractIds);
}
