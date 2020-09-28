package com.banvien.myplatform.core.service;

import com.banvien.myplatform.core.bean.ProjectBean;
import com.banvien.myplatform.core.domain.Project;
import com.banvien.myplatform.core.domain.User;
import com.banvien.myplatform.core.exception.DuplicateException;
import com.banvien.myplatform.core.exception.ObjectNotFoundException;

import java.util.List;

/**
 * <p>Business Service for Users</p>
 * <p>Generated at Sat Sep 29 11:27:04 ICT 2012</p>
 *
 * @author Portal Generatior v1.1 / Generate a complete Spring/Hibernate and Spring MVC webapp
 */
public interface ProjectService extends GenericService<Project, Integer>{
    public Object[] search(ProjectBean searchBean);

    Project findByName(String projectName) throws ObjectNotFoundException;

    void updateItem(ProjectBean projectBean) throws ObjectNotFoundException, DuplicateException;

    void addItem(ProjectBean projectBean) throws DuplicateException, ObjectNotFoundException;

    Integer deleteItems(String[] checkList) throws ObjectNotFoundException;

    public void deleteRelation(Integer projectID);

    public List<User> listDevelopers(ProjectBean projectBean);
}