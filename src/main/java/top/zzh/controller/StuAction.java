package top.zzh.controller;


import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import top.zzh.bean.Clazz;
import top.zzh.bean.Contract;
import top.zzh.bean.Stu;
import top.zzh.bean.StuDetail;
import top.zzh.common.CollectionUtils;
import top.zzh.common.Pager;
import top.zzh.enums.ControllerStatusEnum;
import top.zzh.service.StuService;
import top.zzh.vo.ControllerStatusVO;
import top.zzh.vo.StuClassVO;

import java.util.List;

@Controller
@ParentPackage("base-package")
@Namespace("/stu")
public class StuAction extends BaseAction{

    @Autowired
    private StuService stuService;
    private Pager<Stu> pager;
    private Pager<Contract> contractPager;
    private Stu stu;
    private StuDetail stuDetail;
    private Integer page;
    private Integer rows;
    private Long stuId;
    private String contractIds;
    private ControllerStatusVO statusVO;
    private Pager<StuClassVO> stuClassVOPager;

    @Action(value = "stu_page", results = {@Result(name = "success", location = "/WEB-INF/views/stu/stu.jsp")})
    public String stuPage() {
        return SUCCESS;
    }

    @Action(value = "save", results = {@Result(name = "save", type = "json", params = {"root", "statusVO"})})
    public String save() {
        stuService.save(stu);
        statusVO = ControllerStatusVO.status(ControllerStatusEnum.CLASS_SAVE_SUCCESS);
        return "save";
    }

    @Action(value = "remove", results = {@Result(name = "remove", type = "json", params = {"root", "statusVO"})})
    public String remove() {
        stuService.remove(stu);
        statusVO = ControllerStatusVO.status(ControllerStatusEnum.CLASS_REMOVE_SUCCESS);
        return "remove";
    }


    @Action(value = "update", results = {@Result(name = "update", type = "json", params = {"root", "statusVO"})})
    public String update() {
        stuService.update(stu);
        statusVO = ControllerStatusVO.status(ControllerStatusEnum.CLASS_UPDATE_SUCCESS);
        return "update";
    }


    @Action(value = "pager_criteria",results = {@Result(name = "pager_criteria",type = "json",params = {"root","pager"})})
    public String pagerCriteria(){
        if(stu==null){
            stu = new Stu();
        }
        pager = stuService.listPagerCriteria(page,rows,stu);
        return "pager_criteria";
    }


    @Action(value = "stuvo_list",results = {@Result(name = "stuvo_list",type = "json",params = {"root","stuClassVOPager"})})
    public String stuVo(){
        stuClassVOPager = stuService.listAllWithClass(page,rows,stu);
        return "stuvo_list";
    }

    @Action(value = "save_contract", results = {@Result(name = "saveContract",  type = "json", params = {"root", "statusVO"})})
    public String saveClass() {
        stuService.saveClasses(stuId, contractIds);
        statusVO = ControllerStatusVO.status(ControllerStatusEnum.CLASS_SAVE_SUCCESS);
        return "saveContract";
    }

    @Action(value = "contract", results = {@Result(name = "contractByStuId", type = "json", params = {"root", "contractPager"})})
    public String classByTeacherId() {
        contractPager = new Pager<>();
        List<Contract> contractList = CollectionUtils.setToList(stuService.getById(stuId).getContractSet());
        contractPager.setRows(contractList);
        contractPager.setPageNo(1);
        contractPager.setPageSize(100);
        contractPager.setTotal(new Long(contractList.size()));
        return "contractByStuId";
    }

    public Pager <Stu> getPager() {
        return pager;
    }
    public Stu getStu() {
        return stu;
    }
    public void setStu(Stu stu) {
        this.stu = stu;
    }
    public void setPage(Integer page) {
        this.page = page;
    }
    public void setRows(Integer rows) {
        this.rows = rows;
    }
    public Pager <StuClassVO> getStuClassVOPager() {
        return stuClassVOPager;
    }
    public ControllerStatusVO getStatusVO() {
        return statusVO;
    }
    public StuDetail getStuDetail() {
        return stuDetail;
    }
    public void setStuDetail(StuDetail stuDetail) {
        this.stuDetail = stuDetail;
    }
    public void setStuId(Long stuId) {
        this.stuId = stuId;
    }
    public void setContractIds(String contractIds) {
        this.contractIds = contractIds;
    }
    public Pager <Contract> getContractPager() {
        return contractPager;
    }
}
