package com.banvien.myplatform.core.dao;


import java.sql.SQLException;

import com.banvien.myplatform.core.domain.Useradmin;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;


/**
 * <p>Hibernate DAO layer for Useradmins</p>
 * <p>Generated at date Sat Sep 29 11:27:03 ICT 2012</p>
 *
 * @author Portal Generatior v1.1 / Pojos + Hibernate mapping + Generic DAO 
 */
public class UseradminHibernateDAO extends
		AbstractHibernateDAO<Useradmin, Integer> implements
		UseradminDAO {

	@Override
    public Useradmin findByUsername(final String username) {
        return getHibernateTemplate().execute(
            new HibernateCallback<Useradmin>() {
                public Useradmin doInHibernate(Session session)
                        throws HibernateException, SQLException {
                    Query query = session
                            .createQuery("FROM Useradmin u WHERE u.userName = ?");
                    query.setParameter(0, username);
                    return (Useradmin) query.uniqueResult();
                }
            });
    }
}
