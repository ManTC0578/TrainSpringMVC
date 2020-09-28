package com.banvien.myplatform.core.service;

import com.banvien.myplatform.core.bean.ProjectBean;
import com.banvien.myplatform.core.dao.GenericDAO;
import com.banvien.myplatform.core.dao.UserProjectDAO;
import com.banvien.myplatform.core.dao.ProjectDAO;
import com.banvien.myplatform.core.domain.UserProject;
import com.banvien.myplatform.core.domain.Project;
import com.banvien.myplatform.core.domain.User;
import com.banvien.myplatform.core.exception.DuplicateException;
import com.banvien.myplatform.core.exception.ObjectNotFoundException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>Business Service for Projects</p>
 * <p>Generated at Sat Sep 29 11:27:04 ICT 2012</p>
 *
 * @author Portal Generatior v1.1 / Generate a complete Spring/Hibernate and Spring MVC webapp
 */
public class ProjectServiceImpl extends GenericServiceImpl<Project, Integer> implements ProjectService {
    protected final Log logger = LogFactory.getLog(getClass());

    private ProjectDAO projectDAO;

    public void setProjectDAO (ProjectDAO projectDAO) {
        this.projectDAO = projectDAO;
    }

    private ProjectService projectService;

    private UserProjectDAO userProjectDAO;

    public void setUserProjectDAO(UserProjectDAO userProjectDAO) {
        this.userProjectDAO = userProjectDAO;
    }

    @Override
    protected GenericDAO<Project, Integer> getGenericDAO() {
        return projectDAO;
    }

    public Object[] search(ProjectBean searchBean) {
        return projectDAO.search(searchBean.getPojo().getProjectName(), searchBean.getPojo().getStatus(),
                searchBean.getStartDate(), searchBean.getFromDate(),
                searchBean.getEndDate(), searchBean.getToDate(),
                searchBean.getFirstItem(), searchBean.getMaxPageItems(),
                searchBean.getSortExpression(), searchBean.getSortDirection());
    }

    @Override
    public Project findByName(String projectName) throws ObjectNotFoundException {
        Project res = projectDAO.findEqualUnique("projectName", projectName);
        if (res == null) {
            throw new ObjectNotFoundException("Not found project with project name " + projectName);
        }
        return res;
    }

    @Override
    public void updateItem(ProjectBean projectBean) throws ObjectNotFoundException, DuplicateException {
        Project dbItem = projectDAO.findByIdNoAutoCommit(projectBean.getPojo().getProjectID());
        if (dbItem == null) {
            throw new ObjectNotFoundException("Not found project " + projectBean.getPojo().getProjectID());
        }
        Project pojo = projectBean.getPojo();
        if (dbItem.getUserProjects() != null && dbItem.getUserProjects().size() > 0) {
            userProjectDAO.deleteAll(dbItem.getUserProjects());
        }
        if (projectBean.getCheckList() != null && projectBean.getCheckList().length > 0) {
            for (String userId : projectBean.getCheckList()) {
                User user = new User(Integer.parseInt(userId));
                UserProject userProject = new UserProject();
                userProject.setUser(user);
                userProject.setProject(pojo);
                userProjectDAO.save(userProject);
            }
        }
        dbItem.setProjectName(pojo.getProjectName());
        dbItem.setStartDate(pojo.getStartDate());
        dbItem.setEndDate(pojo.getEndDate());
        dbItem.setStatus(pojo.getStatus());
        projectDAO.detach(dbItem);
        projectDAO.update(dbItem);
    }

    @Override
    public void addItem(ProjectBean projectBean) throws ObjectNotFoundException, DuplicateException {
        Project pojo = projectBean.getPojo();
        projectDAO.save(pojo);
        System.out.println("do it");
        if (projectBean.getCheckList() != null && projectBean.getCheckList().length > 0) {
            for (String userId : projectBean.getCheckList()) {
                User user = new User(Integer.parseInt(userId));
                UserProject userProject = new UserProject();
                userProject.setUser(user);
                userProject.setProject(pojo);
                userProjectDAO.save(userProject);
            }
        }
        projectBean.setPojo(pojo);
    }

    @Override
    public void deleteRelation(Integer projectID){
        List<UserProject> developerList = userProjectDAO.search(projectID);
        Object[] developerArr = developerList.toArray();
        for(int count = 1; count <= developerList.size(); count++){
            userProjectDAO.delete((UserProject) developerArr[count - 1]);
        }
    }

    @Override
    @Transactional
    public Integer deleteItems(String[] checkList) throws ObjectNotFoundException {
        Integer res = 0;
        if(checkList != null && checkList.length > 0){
            res = checkList.length;
            for (String id : checkList) {
                Project dbItem = projectDAO.findByIdNoAutoCommit(Integer.valueOf(id));
                if(dbItem == null){
                    throw new ObjectNotFoundException("Not found project " + id);
                }
                System.out.println("Delele reletion");
//                deleteRelation(dbItem.getProjectID());
//                dbItem.getDevelops().removeAll(dbItem.getDevelops());
//                System.out.println("Delete relation done");
                projectDAO.delete(dbItem);
            }
        }
        return res;
    }

    @Override
    public List<User> listDevelopers(ProjectBean projectBean){
        Project projectPojo = projectBean.getPojo();
        Integer projectID = projectPojo.getProjectID();
        List<UserProject> developerList = userProjectDAO.search(projectID);
        List<User> users = new ArrayList<User>();
        for(int count = 0; count < developerList.size(); count++){
            User user = new User(developerList.get(count).getUser().getUserID());
            users.add(user);
        }
        return users;
    }

}