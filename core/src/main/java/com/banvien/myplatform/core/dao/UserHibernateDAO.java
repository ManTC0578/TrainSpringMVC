package com.banvien.myplatform.core.dao;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.banvien.myplatform.core.Constants;
import com.banvien.myplatform.core.domain.User;
import org.apache.commons.lang.StringUtils;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

/**
 * <p>Hibernate DAO layer for Users</p>
 * <p>Generated at date Sat Sep 29 11:27:04 ICT 2012</p>
 *
 * @author Portal Generatior v1.1 / Pojos + Hibernate mapping + Generic DAO 
 */
public class UserHibernateDAO extends
		AbstractHibernateDAO<User, Integer> implements
		UserDAO {

	@Override
	public Object[] search(final String email, final Byte status,
                           final Date createdDateFrom, final Date createdDateTo,
                           final Integer firstItem, final Integer maxItems, final String sortExpression,
                           final String sortDirection) {
		
		 Object[] res;

	        try{
	            StringBuffer whereClause = new StringBuffer(" 1 = 1 ");
	            if (StringUtils.isNotBlank(email)){
	                whereClause.append(" AND LOWER(u.email) LIKE CONCAT('%', :email, ").append("'%')");
	            }
	            if (createdDateFrom != null) {
	                whereClause.append(" AND u.createdDate >= :createdDateFrom");
	            }
	            if (createdDateTo != null) {
	                whereClause.append(" AND u.createdDate <= :createdDateTo");
	            }
	            if(status != null) {
	            	 whereClause.append(" AND u.status = :status");
	            }

	            final String where = whereClause.toString();
	            List<User> items = getHibernateTemplate().execute(
	                new HibernateCallback<List<User>>() {
	                    public List<User> doInHibernate(Session session)
	                            throws HibernateException, SQLException {
	                        StringBuffer listSQL = new StringBuffer();
	                        listSQL.append("SELECT u FROM User u WHERE ");
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
								listSQL.append(" ORDER BY u.createdDate DESC");
							}
	                        Query query = session
	                                .createQuery(listSQL.toString());
	                        if (StringUtils.isNotBlank(email)){
	                        	query.setParameter("email", email.toLowerCase());
	        	            }
	        	            if (createdDateFrom != null) {
	        	            	query.setParameter("createdDateFrom", createdDateFrom);
	        	            }
	        	            if (createdDateTo != null) {
	        	            	query.setParameter("createdDateTo", createdDateTo);
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
	                        return (List<User>) query.list();
	                    }
	                });

	            Long total = getHibernateTemplate().execute(
	                new HibernateCallback<Long>() {
	                    public Long doInHibernate(Session session)
	                            throws HibernateException, SQLException {
	                        StringBuffer countSQL = new StringBuffer();
	                        countSQL.append(" SELECT COUNT(*) FROM User u WHERE");
	                        countSQL.append(where);
	                        Query query = session.createQuery(countSQL.toString());
	                        if (StringUtils.isNotBlank(email)){
	                        	query.setParameter("email", email.toLowerCase());
	        	            }
	        	            if (createdDateFrom != null) {
	        	            	query.setParameter("createdDateFrom", createdDateFrom);
	        	            }
	        	            if (createdDateTo != null) {
	        	            	query.setParameter("createdDateTo", createdDateTo);
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
	            res = new Object[]{0, new ArrayList<User>()};
	        }
	        return res;
	}

}
