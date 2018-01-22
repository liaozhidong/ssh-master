package top.zzh.bean;

import org.apache.struts2.json.annotations.JSON;

import java.util.Set;


public class Clazz {
    private Long id;
    private String title;

    private Set<Stu> stuSet;
    private Set<Teacher> teacherSet;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @JSON(serialize = false)
    public Set<Stu> getStuSet() {
        return stuSet;
    }

    public void setStuSet(Set<Stu> stuSet) {
        this.stuSet = stuSet;
    }

    @JSON(serialize = false)
    public Set<Teacher> getTeacherSet() {
        return teacherSet;
    }

    public void setTeacherSet(Set<Teacher> teacherSet) {
        this.teacherSet = teacherSet;
    }
}
