package com.banvien.myplatform.core.dao;

import com.banvien.myplatform.core.domain.UserProject;

import java.util.List;

public interface UserProjectDAO extends GenericDAO<UserProject,Integer> {
    public List<UserProject> search(Integer projectID);
}
