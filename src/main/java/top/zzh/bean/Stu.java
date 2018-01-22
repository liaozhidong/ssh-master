package top.zzh.bean;

import org.apache.struts2.json.annotations.JSON;

import java.util.Set;

public class Stu {
    private Long id;
    private String num;
    private String realName;

    private StuDetail stuDetail;
    private Clazz clazz;
    private Set<Contract> contractSet;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public StuDetail getStuDetail() {
        return stuDetail;
    }

    public void setStuDetail(StuDetail stuDetail) {
        this.stuDetail = stuDetail;
    }

    public Clazz getClazz() {
        return clazz;
    }

    public void setClazz(Clazz clazz) {
        this.clazz = clazz;
    }
    @JSON(serialize = false)
    public Set<Contract> getContractSet() {
        return contractSet;
    }

    public void setContractSet(Set<Contract> contractSet) {
        this.contractSet = contractSet;
    }
}
