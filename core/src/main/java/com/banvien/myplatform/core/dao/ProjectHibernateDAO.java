package com.banvien.myplatform.core.dao;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.banvien.myplatform.core.Constants;
import com.banvien.myplatform.core.domain.Project;
import org.apache.commons.lang.StringUtils;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

/**
 * <p>Hibernate DAO layer for Projects</p>
 * <p>Generated at date Sat Sep 29 11:27:04 ICT 2012</p>
 *
 * @author Portal Generatior v1.1 / Pojos + Hibernate mapping + Generic DAO
 */
public class ProjectHibernateDAO extends
        AbstractHibernateDAO<Project, Integer> implements
        ProjectDAO {

    @Override
    public Object[] search(final String name, final Byte status,
                           final Date startDate, final Date fromDate,
                           final Date endDate, final Date toDate,
                           final Integer firstItem, final Integer maxItems,
                           final String sortExpression, final String sortDirection) {

        Object[] res;

        try{
            StringBuffer whereClause = new StringBuffer(" 1 = 1 ");
            if (StringUtils.isNotBlank(name)){
                whereClause.append(" AND LOWER(u.projectName) LIKE CONCAT('%', :name, ").append("'%')");
            }
            if (startDate != null) {
                whereClause.append(" AND u.startDate >= :startDate");
            }
            if (fromDate != null) {
                whereClause.append(" AND u.startDate <= :fromDate");
            }

            if (endDate != null) {
                whereClause.append(" AND u.endDate >= :endDate");
            }
            if (toDate != null) {
                whereClause.append(" AND u.endDate <= :toDate");
            }

            if(status != null) {
                whereClause.append(" AND u.status = :status");
            }

            final String where = whereClause.toString();
            List<Project> items = getHibernateTemplate().execute(
                    new HibernateCallback<List<Project>>() {
                        public List<Project> doInHibernate(Session session)
                                throws HibernateException, SQLException {
                            StringBuffer listSQL = new StringBuffer();
                            listSQL.append("SELECT u FROM Project u WHERE ");
                            listSQL.append(where);
                            if (StringUtils.isNotBlank(sortExpression)) {
                                listSQL.append(" ORDER BY u.").append(
                                        sortExpression);
                                if (StringUtils.isNotBlank(sortDirection)
                                        && Constants.SORT_ASC.equals(sortDirection)) {
                                    listSQL.append(" ASC");
                                } else {
                                    listSQL.append(" DESC");
                                }
                            }else {
                                listSQL.append(" ORDER BY u.startDate DESC");
                            }
                            Query query = session
                                    .createQuery(listSQL.toString());
                            if (StringUtils.isNotBlank(name)){
                                query.setParameter("name", name.toLowerCase());
                            }
                            if (startDate != null) {
                                query.setParameter("startDate", startDate);
                            }
                            if (fromDate != null) {
                                query.setParameter("fromDate", fromDate);
                            }
                            if (endDate != null) {
                                query.setParameter("endDate", endDate);
                            }
                            if (toDate != null) {
                                query.setParameter("toDate", toDate);
                            }

                            if(status != null) {
                                query.setParameter("status", status);
                            }

                            if(firstItem != null) {
                                query.setFirstResult(firstItem);
                            }
                            if(maxItems != null) {
                                query.setMaxResults(maxItems);
                            }
                            return (List<Project>) query.list();
                        }
                    });

            Long total = getHibernateTemplate().execute(
                    new HibernateCallback<Long>() {
                        public Long doInHibernate(Session session)
                                throws HibernateException, SQLException {
                            StringBuffer countSQL = new StringBuffer();
                            countSQL.append(" SELECT COUNT(*) FROM Project u WHERE");
                            countSQL.append(where);
                            Query query = session.createQuery(countSQL.toString());
                            if (StringUtils.isNotBlank(name)){
                                query.setParameter("name", name.toLowerCase());
                            }
                            if (startDate != null) {
                                query.setParameter("startDate", startDate);
                            }
                            if (fromDate != null) {
                                query.setParameter("fromDate", fromDate);
                            }
                            if (endDate != null) {
                                query.setParameter("endDate", endDate);
                            }
                            if (toDate != null) {
                                query.setParameter("toDate", toDate);
                            }
                            if(status != null) {
                                query.setParameter("status", status);
                            }

                            return (Long) query.uniqueResult();
                        }
                    });
            res = new Object[]{total, items};
        }catch (Exception e){
            logger.error(e);
            res = new Object[]{0, new ArrayList<Project>()};
        }
        return res;
    }
}
