package top.zzh.dao.Impl;

import top.zzh.bean.StuDetail;
import top.zzh.common.HibernateUtils;
import top.zzh.common.Pager;
import top.zzh.dao.AppDAOSupport;
import top.zzh.dao.StuDetailDAO;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class StuDetailDAOImpl extends AppDAOSupport implements StuDetailDAO {
    @Override
    public void save(StuDetail stuDetail) {
        getHibernateTemplate().save(stuDetail);
    }

    @Override
    public void remove(StuDetail stuDetail) {
        getHibernateTemplate().delete(stuDetail);
    }

    @Override
    public void removeById(Long id) {
        StuDetail stuDetail = new StuDetail();
        stuDetail.setId(id);
        getHibernateTemplate().delete(stuDetail);
    }

    @Override
    public void update(StuDetail stuDetail) {
        getHibernateTemplate().update(stuDetail);
    }

    @Override
    public StuDetail getById(Long id) {
        return getHibernateTemplate().get(StuDetail.class, id);
    }

    @Override
    public List<StuDetail> listAll() {
        return getHibernateTemplate().loadAll(StuDetail.class);
    }

    @Override
    public Pager<StuDetail> listPager(Pager<StuDetail> pager) {
        return getHibernateTemplate().execute(new HibernateCallback<Pager<StuDetail>>() {
            @Override
            public Pager<StuDetail> doInHibernate(Session session) throws HibernateException {
                Query query = session.createQuery("from StuDetail");
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
                return (Long) session.createQuery("select count(id) from StuDetail").uniqueResult();
            }
        });
    }

    @Override
    public Pager<StuDetail> listPagerCriteria(Pager<StuDetail> pager, StuDetail stuDetail) {
        return getHibernateTemplate().execute(new HibernateCallback<Pager<StuDetail>>() {
            @Override
            public Pager<StuDetail> doInHibernate(Session session) throws HibernateException {
                return HibernateUtils.pager(session, pager, stuDetail, StuDetail.class);
            }
        });
    }

    @Override
    public Long countCriteria(StuDetail stuDetail) {
        return getHibernateTemplate().execute(new HibernateCallback<Long>() {
            @Override
            public Long doInHibernate(Session session) throws HibernateException {
                return HibernateUtils.count(session, stuDetail, StuDetail.class, null);
            }
        });
    }

}
