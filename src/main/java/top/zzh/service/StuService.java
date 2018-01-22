package top.zzh.service;

import top.zzh.bean.Stu;
import top.zzh.common.Pager;
import top.zzh.vo.StuClassVO;
import java.util.*;

public interface StuService extends BaseService<Stu> {

    Pager<StuClassVO> listAllWithClass(int pageNo, int pageSize, Stu stu);
    void saveClasses(Long stuId, String contractIds);
}
