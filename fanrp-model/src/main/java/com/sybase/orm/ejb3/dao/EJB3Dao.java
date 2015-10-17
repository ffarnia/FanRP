/***********************************************************************
 * Module:  EJB3Dao.java
 * Author:  a.gholaminejad
 * Purpose: Defines interface for data access using EJB 3
 ***********************************************************************/
 
package com.sybase.orm.ejb3.dao;

import com.sybase.orm.dao.Dao;
import com.sybase.orm.dao.DaoException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

/**
 * Interface that defines common operations for data access using EJB 3 persisentence technology.
 * 
 */
public interface EJB3Dao extends Dao {

   public void setEntityManagerFactory(EntityManagerFactory entityManagerFactory);

   public EntityManager getEntityManager(boolean newEntityManager);
   
   public EntityManager getEntityManager();
   
   public void save(Object persistentObject) throws DaoException;

   public Object update(Object persistentObject) throws DaoException;

   public void delete(Object persistentObject) throws DaoException;   

   public List findBySQLQuery(String sqlQuery, Class clazz) throws DaoException;

   public List findByEJBQuery(String ejbQuery) throws DaoException;

   public List findByNamedParam(String queryString, String paramName, Object value) throws DaoException;

   public List findByNamedParam(String queryString, String paramName, Object value, int firstResult, int maxResult) throws DaoException;

   public List findByNamedParam(final String queryString, final String[] paramNames, final Object[] values) throws DaoException;

   public List findByNamedParam(final String queryString, final String[] paramNames, final Object[] values, final int firstResult, final int maxResult) throws DaoException;

   public List findByNamedQuery(String queryName) throws DaoException;

   public List findByNamedQuery(String queryName, Object value) throws DaoException;

   public List findByNamedQuery(final String queryName, final Object[] values) throws DaoException;

   public List findByNamedQueryAndNamedParam(String queryName, String paramName, Object value) throws DaoException;

   public List findByNamedQueryAndNamedParam(final String queryName, final String[] paramNames, final Object[] values) throws DaoException;  

   public List find(String queryString, int firstResult, int maxResult) throws DaoException;

   public List find(String queryString) throws DaoException;

   public List find(String queryString, Object value) throws DaoException;

   public List find(final String queryString, final Object[] values) throws DaoException;

   public List find(final String queryString, final Object[] values, final int firstResult, final int maxResult) throws DaoException;

   public <T> T find(Class<T> entityClass, Object primaryKey);
   
   public <T> T getReference(Class<T> entityClass, Object primaryKey);   
   
}