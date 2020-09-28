package com.banvien.myplatform.core.bean;


import java.util.Date;
import java.util.List;
import java.util.Map;

import com.banvien.myplatform.core.domain.Project;
import com.banvien.myplatform.core.domain.User;

public class ProjectBean extends AbstractBean<Project> {

    public ProjectBean(){ this.pojo = new Project(); }

    private Date startDate;
    public Date getStartDate() { return startDate; }
    public void setStartDate(Date startDateFrom) { this.startDate = startDateFrom; }

    private Date fromDate;
    public Date getFromDate() { return fromDate; }
    public void setFromDate(Date fromDate) { this.fromDate = fromDate; }

    private Date endDate;
    public Date getEndDate() { return endDate; }
    public void setEndDate(Date endDate) { this.endDate = endDate; }

    private Date toDate;
    public Date getToDate() { return toDate; }
    public void setToDate(Date toDate) { this.toDate = toDate; }

    private List<User> users;
    public List<User> getUsers() { return users; }
    public void setUsers(List<User> users) { this.users = users; }

    private Map<Integer, Boolean> userIdInProject;
    public Map<Integer, Boolean> getUserIdInProject() { return userIdInProject; }

    public void setUserIdInProject(Map<Integer, Boolean> userIdInProject) { this.userIdInProject = userIdInProject; }
}