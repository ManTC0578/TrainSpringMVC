package com.banvien.myplatform.core.domain;

import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Table(name = "Project")
@Entity
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Project implements Serializable {

    /**
     * Attribute projectID.
     */
    @Column(name = "projectID")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer projectID;
    public Integer getProjectID() { return projectID; }
    public void setProjectID(Integer projectID) { this.projectID = projectID; }

    @OneToMany(mappedBy = "project", fetch = FetchType.LAZY)
    private List<UserProject> userProjects;

    public List<UserProject> getUserProjects() {
        return userProjects;
    }

    public void setDevelops(List<UserProject> develops) {
        this.userProjects = develops;
    }

    @ManyToMany
    @JoinTable(
            name = "UserProject",
            joinColumns = { @JoinColumn(name = "projectID") },
            inverseJoinColumns = { @JoinColumn(name = "userID")}
    )
    private List<User> developers;
    public List<User> getDevelopers() { return developers; }
    public void setDevelopers(List<User> developers) { this.developers = developers; }

    public Project(){}

    public Project(Integer projectID){ this.projectID = projectID; }

    private String projectName;
    public String getProjectName() { return projectName; }
    public void setProjectName(String projectName) { this.projectName = projectName; }

    private Byte status;
    public Byte getStatus() { return status; }
    public void setStatus(Byte status) { this.status = status; }

    private Date startDate;
    public Date getStartDate() { return startDate; }
    public void setStartDate(Date startDate) { this.startDate = startDate; }

    private Date endDate;
    public Date getEndDate() { return endDate; }
    public void setEndDate(Date endDate) { this.endDate = endDate; }
}