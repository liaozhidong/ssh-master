package top.zzh.dao;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import javax.annotation.Resource;

public class AppDAOSupport extends HibernateDaoSupport {

    @Resource(name = "sessionFactory")
    public void  setAppSessionFactory(SessionFactory sessionFactory){
        super.setSessionFactory(sessionFactory);
    }
}
