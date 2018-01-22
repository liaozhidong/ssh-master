package top.zzh.dao.Impl;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.stereotype.Repository;
import top.zzh.bean.Clazz;
import top.zzh.common.HibernateUtils;
import top.zzh.common.Pager;
import top.zzh.dao.AppDAOSupport;
import top.zzh.dao.ClazzDAO;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

@Repository
public class ClazzDAOImpl extends AppDAOSupport implements ClazzDAO {

    @Override
    public void save(Clazz clazz) {
        getHibernateTemplate().save(clazz);
    }

    @Override
    public void remove(Clazz clazz) {
        if(clazz!=null){
            getHibernateTemplate().delete(clazz);
        }
    }

    @Override
    public void removeById(Long id) {
        Clazz clazz = new Clazz();
        clazz.setId(id);
        getHibernateTemplate().delete(clazz);
    }

    @Override
    public void update(Clazz clazz) {
        getHibernateTemplate().update(clazz);
    }

    @Override
    public Clazz getById(Long id) {
        return getHibernateTemplate().get(Clazz.class, id);
    }

    @Override
    public List<Clazz> listAll() {
        return getHibernateTemplate().loadAll(Clazz.class);
    }

    @Override
    public Pager<Clazz> listPager(Pager<Clazz> pager) {
        return getHibernateTemplate().execute(new HibernateCallback<Pager<Clazz>>() {
            @Override
            public Pager<Clazz> doInHibernate(Session session) throws HibernateException {
                Query query = session.createQuery("from Clazz");
                query.setFirstResult(pager.getBeginIndex());
                query.setMaxResults(pager.getPageSize());
                pager.setRows(query.list());
                return pager;
            }
        });
    }

    @Override
    public Long count() {
        return getHibernateTemplate().execute(new HibernateCallback<Long>() {
            @Override
            public Long doInHibernate(Session session) throws HibernateException {
                return (Long) session.createQuery("select count(id) from Clazz").uniqueResult();
            }
        });
    }

    @Override
    public Pager <Clazz> listPagerCriteria(Pager <Clazz> pager, Clazz clazz) {
        return getHibernateTemplate().execute(new HibernateCallback<Pager<Clazz>>() {
            @Override
            public Pager<Clazz> doInHibernate(Session session) throws HibernateException {
               return HibernateUtils.pager(session,pager,clazz,Clazz.class);
            }
        });
    }


    @Override
    public Long countCriteria(Clazz clazz) {
        return getHibernateTemplate().execute(new HibernateCallback <Long>() {
            @Override
            public Long doInHibernate(Session session) throws HibernateException {
                return HibernateUtils.count(session,clazz,Clazz.class,null);
            }
        });
    }

}
