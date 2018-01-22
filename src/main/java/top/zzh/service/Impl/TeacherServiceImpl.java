package top.zzh.service.Impl;

import top.zzh.bean.Teacher;
import top.zzh.common.Pager;
import top.zzh.dao.TeacherDAO;
import top.zzh.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    private TeacherDAO teacherDAO;

    @Override
    public void save(Teacher teacher) {
        teacherDAO.save(teacher);
    }

    @Override
    public void remove(Teacher teacher) {
        teacherDAO.remove(teacher);
    }

    @Override
    public void removeById(Long id) {
        teacherDAO.removeById(id);
    }

    @Override
    public void update(Teacher teacher) {
        teacherDAO.update(teacher);
    }

    @Override
    public Teacher getById(Long id) {
        return teacherDAO.getById(id);
    }

    @Override
    public List<Teacher> listAll() {
        return teacherDAO.listAll();
    }

    @Override
    public Pager<Teacher> listPager(int pageNo, int pageSize) {
        Pager<Teacher> pager = new Pager<>(pageNo, pageSize);
        pager = teacherDAO.listPager(pager);
        pager.setTotal(teacherDAO.count());
        return pager;
    }

    @Override
    public Pager<Teacher> listPagerCriteria(int pageNo, int pageSize, Teacher teacher) {
        Pager<Teacher> pager = new Pager<>(pageNo, pageSize);
        pager.setTotal(teacherDAO.countCriteria(teacher));
        return teacherDAO.listPagerCriteria(pager, teacher);
    }

    @Override
    public void saveClasses(Long teacherId, String classIds) {
        String[] ary = classIds.split(",");
        List<Long> classIdArray = new ArrayList<>();
        for (String classId : ary) {
            classIdArray.add(Long.valueOf(classId));
        }
        teacherDAO.saveClasses(teacherId, classIdArray);
    }
}
