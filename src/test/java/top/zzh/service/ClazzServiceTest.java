package top.zzh.service;


import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import top.zzh.bean.Clazz;
import top.zzh.bean.Stu;
import top.zzh.common.Pager;

import java.util.HashSet;
import java.util.Set;

public class ClazzServiceTest extends BaseServiceTest{

    @Autowired
    private ClazzService clazzService;

    @Test
    public void testSave(){
        Clazz clazz = new Clazz();
        clazz.setTitle("1604");
        Set<Stu> stuSet = new HashSet<>();
        Stu stu = new Stu();
        stu.setNum("160401");
        stu.setRealName("AA");
        stuSet.add(stu);
        clazz.setStuSet(stuSet);
        clazzService.save(clazz);
    }

    @Test
    public void testGetById() {
        Clazz clazz = clazzService.getById(1L);
        System.out.println(clazz.getTitle());
        Set<Stu> stuSet = clazz.getStuSet();
        System.out.println(stuSet.iterator().next().getRealName());
    }

    @Test
    public void testListPager() {
        Pager<Clazz> pager = clazzService.listPager(1, 2);
        System.out.println(pager.getTotal());
        System.out.println(pager.getPages());
        System.out.println(pager.getRows().get(0).getTitle());
    }

}
