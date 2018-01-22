package top.zzh.bean;

import java.util.Set;

public class Contract {
    private Long id;
    private String realName;
    private String phone;

    private Set<Stu> stuSet;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Set<Stu> getStuSet() {
        return stuSet;
    }

    public void setStuSet(Set<Stu> stuSet) {
        this.stuSet = stuSet;
    }
}
