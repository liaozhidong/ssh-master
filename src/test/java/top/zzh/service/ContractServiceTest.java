package top.zzh.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import top.zzh.bean.Contract;
import top.zzh.bean.Stu;
import java.util.HashSet;
import java.util.Set;

public class ContractServiceTest extends BaseServiceTest {

    @Autowired
    private ContractService contractService;

    @Test
    public void testSave() {
        Contract contract = new Contract();
        contract.setPhone("13666666666");
        contract.setRealName("张三他妈");
        Set<Stu> stuSet = new HashSet<>();
        Stu stu = new Stu();
        stu.setId(1L);
        stuSet.add(stu);
        Stu stu1 = new Stu();
        stu1.setId(3L);
        stuSet.add(stu1);
        contract.setStuSet(stuSet);
        contractService.save(contract);
    }

}
