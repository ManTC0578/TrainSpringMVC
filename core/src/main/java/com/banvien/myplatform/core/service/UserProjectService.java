package com.banvien.myplatform.core.service;

import com.banvien.myplatform.core.bean.UserProjectBean;
import com.banvien.myplatform.core.domain.UserProject;
import com.banvien.myplatform.core.exception.DuplicateException;

public interface UserProjectService extends GenericService<UserProject, Integer>{
    public int developerCount(UserProjectBean userProjectBean);
    void addItem(UserProjectBean userProjectBean) throws DuplicateException;
}
