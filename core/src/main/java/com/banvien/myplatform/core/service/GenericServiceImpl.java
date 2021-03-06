package com.banvien.myplatform.core.service;


import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.banvien.myplatform.core.dao.GenericDAO;
import com.banvien.myplatform.core.exception.DuplicateException;
import com.banvien.myplatform.core.exception.ObjectNotFoundException;

/**
 *
 * @author ban
 *
 * @param <T>
 * @param <ID>
 */
public abstract class GenericServiceImpl<T, ID extends Serializable>
        implements GenericService<T, ID> {

    protected abstract GenericDAO<T, ID> getGenericDAO();

    @SuppressWarnings("unchecked")
	public Class getPersistentClass() {
        return getGenericDAO().getPersistentClass();
    }

    public T findById(ID id) throws ObjectNotFoundException {
    	
        T entity = (T) getGenericDAO().findById(id, false);
        if (entity == null) throw new ObjectNotFoundException("database.exception.objectnotfound");
        return entity;
    }

    public T findEqualUnique(String property, Object value) throws ObjectNotFoundException{
        T entity = getGenericDAO().findEqualUnique(property, value);
        if (entity == null) throw new ObjectNotFoundException("database.exception.objectnotfound");
        return entity;
    }

    @Override
    public void deleteAll(List<T> entities) {
        getGenericDAO().deleteAll(entities);
    }
    
    



    /**
     * Find by id. This api must be
     * executed within an transaction with explicitly commit because
     * connection's autocommit is set to false.
     *
     * @param id
     * @return
     * @throws ObjectNotFoundException 
     */
    public T findByIdNoCommit(ID id) throws ObjectNotFoundException {
        T entity = (T) getGenericDAO().findByIdNoAutoCommit(id);
        if (entity == null) throw new ObjectNotFoundException("database.exception.objectnotfound");
        return entity;
    }

    public T save(T entity) throws DuplicateException {
    	try{
	        getGenericDAO().save(entity);
	        return entity;
    	}catch (Exception e) {
    		throw new DuplicateException("database.exception.objectduplicate", e);
		}
    }

    @Override
    public void saveAll(Collection entities) throws DuplicateException {
        try {
            getGenericDAO().saveAll(entities);
        } catch (Exception e) {
            throw new DuplicateException("database.exception.objectduplicate", e);
        }
    }

    public T update(T entity) throws DuplicateException {
    	try{
	        getGenericDAO().update(entity);
	        return entity;	        
    	}catch (Exception e) {
    		throw new DuplicateException("database.exception.objectupdate", e);
		}
    }

    public T saveOrUpdate(T entity) throws DuplicateException {
    	try{
	        getGenericDAO().saveOrUpdate(entity);
	        return entity;
    	}catch (Exception e) {
    		throw new DuplicateException("database.exception.objectduplicate", e);
		}
    }

    public void delete(T entity) {
        this.getGenericDAO().delete(entity);
    }

    public void detach(T entity) {
        this.getGenericDAO().detach(entity);
    }

    public List<T> find(int maxResults) {
        return this.getGenericDAO().find(maxResults);
    }

    public List<T> findAll() {
        return this.getGenericDAO().findAll();
    }

    @Override
    public List<T> findProperties(Map<String, Object> propertyNameValues) {
        return getGenericDAO().findProperties(propertyNameValues);
    }

    @Override
    public List<T> findProperty(String property, Object value) {
        return getGenericDAO().findProperty(property, value);
    }
    
    
}


