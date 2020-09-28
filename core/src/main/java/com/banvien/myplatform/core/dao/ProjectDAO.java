package com.banvien.myplatform.core.dao;

import com.banvien.myplatform.core.domain.Project;

import java.util.Date;

/**
 * <p>Generic DAO layer for Users</p>
 * <p>Generated at date Sat Sep 29 11:27:04 ICT 2012</p>
 *
 * @author Portal Generatior v1.1 / Pojos + Hibernate mapping + Generic DAO
 *
 */
public interface ProjectDAO extends GenericDAO<Project,Integer> {
    public Object[] search(String name, Byte status,
                           Date startDate, Date fromDate,
                           Date endDate, Date toDate,
                           Integer firstItem, Integer maxItems,
                           String sortExpression, String sortDirection);
}