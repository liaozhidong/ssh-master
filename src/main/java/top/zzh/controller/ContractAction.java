package top.zzh.controller;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import top.zzh.bean.Contract;
import top.zzh.common.Pager;
import top.zzh.enums.ControllerStatusEnum;
import top.zzh.service.ContractService;
import top.zzh.vo.ControllerStatusVO;

@Controller
@Namespace("/contract")
@ParentPackage("base-package")
public class ContractAction extends BaseAction {

    @Autowired
    private ContractService contractService;
    private Contract contract;
    private Pager<Contract> pager;
    private ControllerStatusVO statusVO;
    private Integer page;
    private Integer rows;

    @Action(value = "contract_page", results = {@Result(name = "success", location = "/WEB-INF/views/contract/contract.jsp")})
    public String contractPage() {
        return SUCCESS;
    }

    @Action(value = "save", results = {@Result(name = "save",  type = "json", params = {"root", "statusVO"})})
    public String save() {
        contractService.save(contract);
        statusVO = ControllerStatusVO.status(ControllerStatusEnum.CLASS_SAVE_SUCCESS);
        return "save";
    }

    @Action(value = "remove", results = {@Result(name = "remove", type = "json",  params = {"root", "statusVO"})})
    public String remove() {
        contractService.remove(contract);
        statusVO = ControllerStatusVO.status(ControllerStatusEnum.CLASS_REMOVE_SUCCESS);
        return "remove";
    }

    @Action(value = "update", results = {@Result(name = "update", type = "json",  params = {"root", "statusVO"})})
    public String update() {
        contractService.update(contract);
        statusVO = ControllerStatusVO.status(ControllerStatusEnum.CLASS_UPDATE_SUCCESS);
        return "update";
    }

    @Action(value = "pager_criteria", results = {@Result(name = "pager_criteria", type = "json",  params = {"root", "pager"})})
    public String pagerCriteria() {
        pager = contractService.listPagerCriteria(page, rows, contract);
        return "pager_criteria";
    }

    public Contract getContract() {
        return contract;
    }
    public void setContract(Contract contract) {
        this.contract = contract;
    }
    public Pager <Contract> getPager() {
        return pager;
    }
    public ControllerStatusVO getStatusVO() {
        return statusVO;
    }
    public void setPage(Integer page) {
        this.page = page;
    }
    public void setRows(Integer rows) {
        this.rows = rows;
    }
}
