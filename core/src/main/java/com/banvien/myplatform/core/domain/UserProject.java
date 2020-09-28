package com.banvien.myplatform.core.domain;

import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "UserProject")
@Entity
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class UserProject implements Serializable {

    @Column(name = "userProjectID")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer userProjectID;
    public Integer getUserProjectID() { return userProjectID; }
    public void setUserProjectID(Integer userProjectID) { this.userProjectID = userProjectID; }

    public UserProject(){}

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "projectID", referencedColumnName = "projectID")
    private Project project;
    public Project getProject() { return project; }
    public void setProject(Project project) { this.project = project; }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userID", referencedColumnName = "userID")
    private User user;
    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
}

