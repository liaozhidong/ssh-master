package top.zzh.dao;

import top.zzh.bean.Teacher;
import java.util.*;

public interface TeacherDAO extends BaseDAO<Teacher> {

    void saveClasses(Long teacherId, List<Long> classIds);
}
