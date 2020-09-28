package com.banvien.myplatform.core.service;


import com.banvien.myplatform.core.bean.UseradminBean;
import com.banvien.myplatform.core.dao.GenericDAO;
import com.banvien.myplatform.core.dao.UseradminDAO;
import com.banvien.myplatform.core.domain.Useradmin;
import com.banvien.myplatform.core.exception.DuplicateException;
import com.banvien.myplatform.core.exception.ObjectNotFoundException;
import com.banvien.myplatform.core.utils.DesEncrypterUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
/**
 * <p>Business Service for Useradmins</p>
 * <p>Generated at Sat Sep 29 11:27:03 ICT 2012</p>
 *
 * @author Portal Generatior v1.1 / Generate a complete Spring/Hibernate and Spring MVC webapp
 */
public class UseradminServiceImpl extends GenericServiceImpl<Useradmin, Integer> implements UseradminService {
	protected final Log logger = LogFactory.getLog(getClass());
	private UseradminDAO useradminDAO;
	
	public void setUseradminDAO (UseradminDAO useradminDAO) {
		this.useradminDAO = useradminDAO;
	}
	@Override
    protected GenericDAO<Useradmin, Integer> getGenericDAO() {
        return useradminDAO;
    }				

	public Object[] search(UseradminBean searchBean) {
		Map<String, Object> propertyNameValues = new HashMap<String, Object>();
		          		if (searchBean.getPojo().getUserName()  != null  &&  StringUtils.isNotBlank("" + searchBean.getPojo().getUserName() ) ) {
			propertyNameValues.put("userName",  searchBean.getPojo().getUserName());
		}     	 	
		          		if (searchBean.getPojo().getPassword()  != null  &&  StringUtils.isNotBlank("" + searchBean.getPojo().getPassword() ) ) {
			propertyNameValues.put("password",  searchBean.getPojo().getPassword());
		}     	 	
		          		if (searchBean.getPojo().getFullName()  != null  &&  StringUtils.isNotBlank("" + searchBean.getPojo().getFullName() ) ) {
			propertyNameValues.put("fullName",  searchBean.getPojo().getFullName());
		}     	 	
		          		if (searchBean.getPojo().getEmail()  != null  &&  StringUtils.isNotBlank("" + searchBean.getPojo().getEmail() ) ) {
			propertyNameValues.put("email",  searchBean.getPojo().getEmail());
		}     	 	
		          		if (searchBean.getPojo().getStatus()  != null  &&  StringUtils.isNotBlank("" + searchBean.getPojo().getStatus() ) ) {
			propertyNameValues.put("status",  searchBean.getPojo().getStatus());
		}     	 	
		          		if (searchBean.getPojo().getRole()  != null  &&  StringUtils.isNotBlank("" + searchBean.getPojo().getRole() ) ) {
			propertyNameValues.put("role",  searchBean.getPojo().getRole());
		}     	 	
		          		                 
		return useradminDAO.searchByProperties(propertyNameValues, searchBean.getFirstItem(), searchBean.getMaxPageItems(), searchBean.getSortExpression(), searchBean.getSortDirection(), true, null);
	}	
	 @Override
	    public Useradmin findByEmail(String email) throws ObjectNotFoundException {
	        Useradmin res = useradminDAO.findEqualUnique("email", email);
	        if (res == null) {
	        	throw new ObjectNotFoundException("Not found user with email " + email);
	        }
	        return res;
	    }

	    @Override
	    public Useradmin findByUsername(String userName) throws ObjectNotFoundException {
	        Useradmin res = useradminDAO.findEqualUnique("userName", userName);
	        if (res == null) {
	        	throw new ObjectNotFoundException("Not found user with username " + userName);
	        }
	        return res;
	    }

	    @Override
	    public void updateItem(UseradminBean useradminBean) throws ObjectNotFoundException, DuplicateException {
	        Useradmin dbItem = useradminDAO.findByIdNoAutoCommit(useradminBean.getPojo().getUserAdminID());
	        if (dbItem == null) {
	        	throw new ObjectNotFoundException("Not found user " + useradminBean.getPojo().getUserAdminID());
	        }

	        Useradmin pojo = useradminBean.getPojo();
	        if (StringUtils.isNotEmpty(pojo.getPassword())) {
	            pojo.setPassword(DesEncrypterUtils.getInstance().encrypt(pojo.getPassword()));
	        }else{
	            pojo.setPassword(dbItem.getPassword());
	        }
	        pojo.setCreatedDate(dbItem.getCreatedDate());
	        pojo.setModifiedDate(new Timestamp(new Date().getTime()));
	        useradminDAO.detach(dbItem);
	        useradminDAO.update(pojo);
	    }

	    @Override
	    public void addItem(UseradminBean useradminBean) throws DuplicateException {
	        Useradmin pojo = useradminBean.getPojo();
	        pojo.setCreatedDate(new Timestamp(new Date().getTime()));
	        pojo.setPassword(DesEncrypterUtils.getInstance().encrypt(pojo.getPassword()));
	        pojo = useradminDAO.save(pojo);
	        useradminBean.setPojo(pojo);
	    }

	    @Override
	    public Integer deleteItems(String[] checkList) {
	        Integer res = 0;
	        if (checkList != null && checkList.length > 0) {
	            res = checkList.length;
	            for (String id : checkList) {
	            	Useradmin userAdmin = new Useradmin(Integer.valueOf(id));
	                useradminDAO.delete(userAdmin);
	            }
	        }
	        return res;
	    }
	    @Override
	    public void updateProfile(UseradminBean useradminBean) throws ObjectNotFoundException, DuplicateException {
	        Useradmin dbItem = useradminDAO.findByIdNoAutoCommit(useradminBean.getPojo().getUserAdminID());
	        if (dbItem == null) {
	        	throw new ObjectNotFoundException("Not found user " + useradminBean.getPojo().getUserAdminID());
	        }

	        if (StringUtils.isNotEmpty(useradminBean.getNewPassword())) {
	            dbItem.setPassword(DesEncrypterUtils.getInstance().encrypt(useradminBean.getNewPassword()));
	        }
	        dbItem.setEmail(useradminBean.getPojo().getEmail());
	        dbItem.setFullName(useradminBean.getPojo().getFullName());

	        useradminDAO.update(dbItem);
	    }
}