package top.zzh.service;


import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import top.zzh.bean.Clazz;
import top.zzh.bean.Stu;
import top.zzh.bean.StuDetail;

public class StuServiceTest extends BaseServiceTest {

    @Autowired
    private StuService stuService;

    @Test
    public void testSave() {
        Stu stu = new Stu();
        Clazz clazz = new Clazz();
        clazz.setId(1L);
        stu.setClazz(clazz);
        stu.setNum("160107");
        stu.setRealName("张三");

        StuDetail stuDetail = new StuDetail();
        stuDetail.setGender((byte) 0);
        stuDetail.setIdentityNo("188");
        stuDetail.setPhone("18888888888");
        stuDetail.setStu(stu);
        stu.setStuDetail(stuDetail);
        stuService.save(stu);
    }

    @Test
    public void testGetById() {
        Stu stu = stuService.getById(3L);
        System.out.println(stu.getStuDetail().getPhone());
    }
}
