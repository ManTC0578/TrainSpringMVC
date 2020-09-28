package com.banvien.myplatform.core.service;


import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.banvien.myplatform.core.exception.DuplicateException;
import com.banvien.myplatform.core.exception.ObjectNotFoundException;

public interface GenericService<T, ID extends Serializable> {

    @SuppressWarnings("unchecked")
	public Class getPersistentClass();
    /**
     * Find by id. This API find object by identifier
     * @param id
     * @return the object with specified input identifier
     * @throws ObjectNotFoundException if null object value return
     */
    public T findById(ID id) throws ObjectNotFoundException;

    public T findEqualUnique(String property, Object value) throws ObjectNotFoundException;

    /**
     * Find by id. This api must be
     * executed within an transaction with explicitly commit because
     * connection's autocommit is set to false.
     *
     * @param id
     * @return
     */
    public T findByIdNoCommit(ID id) throws ObjectNotFoundException;

    public T save(T entity) throws DuplicateException;

    public void saveAll(final Collection entities) throws DuplicateException;

    public T update(T entity) throws DuplicateException;

    public T saveOrUpdate(T entity) throws DuplicateException;

    public void delete(T entity);
    public void deleteAll(List<T> entity);

    public void detach(T entity);

    public List<T> find(int maxResults);

    public List<T> findAll();
    
    public List<T> findProperty(final String property, final Object value);
    public List<T> findProperties(Map<String, Object> propertyNameValues);
}