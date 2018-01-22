package top.zzh.service;

import top.zzh.bean.Teacher;


public interface TeacherService extends BaseService<Teacher> {

    void saveClasses(Long teacherId, String classIds);
}
