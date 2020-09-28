package com.banvien.myplatform.core.domain;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "ProjectMember")
@Entity
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ProjectMember implements Serializable {

    public ProjectMember(){}

    @Column(name = "projectMemberID")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer projectMemberID;

    public Integer getProjectMemberID() {
        return projectMemberID;
    }

    public void setProjectMemberID(Integer projectMemberID) {
        this.projectMemberID = projectMemberID;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "projectID", referencedColumnName = "projectID")
    private Project project;

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }
}
