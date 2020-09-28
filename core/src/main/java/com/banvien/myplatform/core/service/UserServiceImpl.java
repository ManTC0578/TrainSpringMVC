package com.banvien.myplatform.core.service;

import com.banvien.myplatform.core.Constants;
import com.banvien.myplatform.core.bean.UserBean;
import com.banvien.myplatform.core.dao.GenericDAO;
import com.banvien.myplatform.core.dao.UserDAO;
import com.banvien.myplatform.core.domain.User;
import com.banvien.myplatform.core.exception.DuplicateException;
import com.banvien.myplatform.core.exception.ObjectNotFoundException;
import com.banvien.myplatform.core.utils.DesEncrypterUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.sql.Timestamp;
import java.util.Date;
/**
 * <p>Business Service for Users</p>
 * <p>Generated at Sat Sep 29 11:27:04 ICT 2012</p>
 *
 * @author Portal Generatior v1.1 / Generate a complete Spring/Hibernate and Spring MVC webapp
 */
public class UserServiceImpl extends GenericServiceImpl<User, Integer> implements UserService {
	protected final Log logger = LogFactory.getLog(getClass());
	private UserDAO userDAO;
	
	public void setUserDAO (UserDAO userDAO) {
		this.userDAO = userDAO;
	}
	@Override
    protected GenericDAO<User, Integer> getGenericDAO() {
        return userDAO;
    }				

	public Object[] search(UserBean searchBean) {
		return userDAO.search(searchBean.getPojo().getEmail(), searchBean.getPojo().getStatus(), searchBean.getCreatedDateFrom(), searchBean.getCreatedDateTo(), searchBean.getFirstItem(), searchBean.getMaxPageItems(), searchBean.getSortExpression(), searchBean.getSortDirection());
	}	
	@Override
    public User findByEmail(String email) throws ObjectNotFoundException {
        User res = userDAO.findEqualUnique("email", email);
        if (res == null) {
        	throw new ObjectNotFoundException("Not found user with email " + email);
        }
        return res;
    }

    @Override
    public void updateItem(UserBean userBean) throws ObjectNotFoundException, DuplicateException {
        User dbItem = userDAO.findByIdNoAutoCommit(userBean.getPojo().getUserID());
        if (dbItem == null) {
        	throw new ObjectNotFoundException("Not found user " + userBean.getPojo().getUserID());
        }
        User pojo = userBean.getPojo();
        if (StringUtils.isNotEmpty(pojo.getPassword())) {
        	dbItem.setPassword(DesEncrypterUtils.getInstance().encrypt(pojo.getPassword()));
        }
        dbItem.setFullName(pojo.getFullName());
        dbItem.setEmail(pojo.getEmail());
        dbItem.setStatus(pojo.getStatus());
        dbItem.setModifiedDate(new Timestamp(new Date().getTime()));
        userDAO.update(dbItem);
    }

    @Override
    public void addItem(UserBean userBean) throws DuplicateException {
        User pojo = userBean.getPojo();
        pojo.setCreatedDate(new Timestamp(new Date().getTime()));
        pojo.setPassword(DesEncrypterUtils.getInstance().encrypt(pojo.getPassword()));

        userDAO.save(pojo);
        userBean.setPojo(pojo);
    }

    @Override
    public Integer deleteItems(String[] checkList) throws ObjectNotFoundException{
        Integer res = 0;
        if (checkList != null && checkList.length > 0) {
            res = checkList.length;
            for (String id : checkList) {
                User dbItem = userDAO.findByIdNoAutoCommit(Integer.valueOf(id));
                if (dbItem == null) {
                	throw new ObjectNotFoundException("Not found user " + id);
                }
                dbItem.setStatus(Constants.USER_DISABLED);
                userDAO.update(dbItem);
            }
        }
        return res;
    }
}