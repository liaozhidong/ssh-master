package top.zzh.controller;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import top.zzh.bean.StuDetail;
import top.zzh.enums.ControllerStatusEnum;
import top.zzh.service.StuDetailService;
import top.zzh.vo.ControllerStatusVO;

@Controller
@ParentPackage("base-package")
@Namespace("/stuDetail")
public class StuDetailAction {

    @Autowired
    private StuDetailService stuDetailService;
    private StuDetail stuDetail;
    private ControllerStatusVO statusVO;
    private Long stuId;

    @Action(value = "get", results = {@Result(name = "get", type = "json", params = {"root", "stuDetail"})})
    public String byId() {
        stuDetail = stuDetailService.getById(stuId);
        return "get";
    }

    @Action(value = "save", results = {@Result(name = "save",  type = "json", params = {"root", "statusVO"})})
    public String save() {
        stuDetailService.save(stuDetail);
        statusVO = ControllerStatusVO.status(ControllerStatusEnum.CLASS_SAVE_SUCCESS);
        return "save";
    }

    @Action(value = "remove", results = {@Result(name = "remove", type = "json",  params = {"root", "statusVO"})})
    public String remove() {
        stuDetailService.remove(stuDetail);
        statusVO = ControllerStatusVO.status(ControllerStatusEnum.CLASS_REMOVE_SUCCESS);
        return "remove";
    }

    @Action(value = "update", results = {@Result(name = "update", type = "json",  params = {"root", "statusVO"})})
    public String update() {
        if (stuDetail.getId() == null) {
            stuDetailService.save(stuDetail);
        } else {
            stuDetailService.update(stuDetail);
        }
        statusVO = ControllerStatusVO.status(ControllerStatusEnum.CLASS_UPDATE_SUCCESS);
        return "update";
    }


    public ControllerStatusVO getStatusVO() {
        return statusVO;
    }
    public void setStuDetail(StuDetail stuDetail) {
        this.stuDetail = stuDetail;
    }
    public StuDetail getStuDetail() {
        return stuDetail;
    }
    public void setStuId(Long stuId) {
        this.stuId = stuId;
    }
}
