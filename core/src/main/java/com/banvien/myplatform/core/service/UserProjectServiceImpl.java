package com.banvien.myplatform.core.service;

import com.banvien.myplatform.core.bean.UserProjectBean;
import com.banvien.myplatform.core.dao.GenericDAO;
import com.banvien.myplatform.core.dao.UserProjectDAO;
import com.banvien.myplatform.core.domain.UserProject;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * <p>Business Service for Users</p>
 * <p>Generated at Sat Sep 29 11:27:04 ICT 2012</p>
 *
 * @author Portal Generatior v1.1 / Generate a complete Spring/Hibernate and Spring MVC webapp
 */
public class UserProjectServiceImpl extends GenericServiceImpl<UserProject, Integer> implements UserProjectService {
    protected final Log logger = LogFactory.getLog(getClass());
    private UserProjectDAO userProjectDAO;

    public void setDevelopDAO (UserProjectDAO userProjectDAO) {
        this.userProjectDAO = userProjectDAO;
    }
    @Override
    protected GenericDAO<UserProject, Integer> getGenericDAO() {
        return userProjectDAO;
    }

    @Override
    public int developerCount (UserProjectBean userProjectBean){
        return 1;
    }

    @Override
    public void addItem(UserProjectBean userProjectBean){
        UserProject pojo = userProjectBean.getPojo();
    }
}

