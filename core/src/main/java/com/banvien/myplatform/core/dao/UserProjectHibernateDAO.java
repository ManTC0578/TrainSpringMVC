package com.banvien.myplatform.core.dao;

import java.sql.SQLException;
import java.util.List;

import com.banvien.myplatform.core.domain.UserProject;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;


public class UserProjectHibernateDAO extends
        AbstractHibernateDAO<UserProject, Integer> implements
        UserProjectDAO {
    @Override
    public List<UserProject> search(Integer projectID) {
        Object[] res;
        StringBuffer whereClause = new StringBuffer("ProjectID = ");
        whereClause.append(projectID);

        final String where = whereClause.toString();
        List<UserProject> items = getHibernateTemplate().execute(
                new HibernateCallback<List<UserProject>>() {
                    public List<UserProject> doInHibernate(Session session)
                            throws HibernateException, SQLException {
                        StringBuffer listSQL = new StringBuffer();
                        listSQL.append("SELECT us FROM Develop us WHERE ");
                        listSQL.append(where);
                        Query query = session
                                .createQuery(listSQL.toString());
                        return (List<UserProject>) query.list();
                    }
                }
        );
        return items;
    }
}

