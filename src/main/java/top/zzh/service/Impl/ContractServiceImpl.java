package top.zzh.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.zzh.bean.Contract;
import top.zzh.common.Pager;
import top.zzh.dao.ContractDAO;
import top.zzh.service.ContractService;

import java.util.List;

@Service
public class ContractServiceImpl implements ContractService {

    @Autowired
    private ContractDAO contractDAO;

    @Override
    public void save(Contract contract) {
        contractDAO.save(contract);
    }

    @Override
    public void remove(Contract contract) {
        contractDAO.remove(contract);
    }

    @Override
    public void removeById(Long id) {
        contractDAO.removeById(id);
    }

    @Override
    public void update(Contract contract) {
        contractDAO.update(contract);
    }

    @Override
    public Contract getById(Long id) {
        return contractDAO.getById(id);
    }

    @Override
    public List<Contract> listAll() {
        return contractDAO.listAll();
    }

    @Override
    public Pager<Contract> listPager(int pageNo, int pageSize) {
        Pager<Contract> pager = new Pager<>(pageNo, pageSize);
        pager = contractDAO.listPager(pager);
        pager.setTotal(contractDAO.count());
        return pager;
    }

    @Override
    public Pager<Contract> listPagerCriteria(int pageNo, int pageSize, Contract contract) {
        Pager<Contract> pager = new Pager<>(pageNo, pageSize);
        pager.setTotal(contractDAO.countCriteria(contract));
        return contractDAO.listPagerCriteria(pager, contract);
    }

}
