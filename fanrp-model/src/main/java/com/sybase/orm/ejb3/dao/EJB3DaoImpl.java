/***********************************************************************
 * Module:  EJB3DaoImpl.java
 * Author:  a.gholaminejad
 * Purpose: Defines implementation class of EJB3Dao interface
 ***********************************************************************/
 
package com.sybase.orm.ejb3.dao;

import com.sybase.orm.dao.DaoException;
import com.sybase.orm.ejb3.EJB3PersistenceManager;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Class that implements <Code>EJB3Dao</Code> interface.
 * 
 */
public class EJB3DaoImpl implements EJB3Dao {

   private boolean autoCommit = true;

   private boolean autoCloseEntityManager = false;

   private Log log = LogFactory.getLog(EJB3DaoImpl.class);

   @PersistenceContext
   private  EntityManager em;


   /**
    * Empty constructor
    */
   public EJB3DaoImpl() {
      super();
   }

   /**
    * Constructor with specified entity manager factory
    * 
    * @param entityManagerFactory
    */
   public EJB3DaoImpl(EntityManagerFactory entityManagerFactory) {
      setEntityManagerFactory(entityManagerFactory);
   }


   /*
    * (non-Javadoc)
    *
    * @see com.sybase.EJB3Dao#setEntityManagerFactory(javax.persistence.EntityManagerFactory)
    */
   public void setEntityManagerFactory(EntityManagerFactory factory) {
      throw new UnsupportedOperationException("Entity manager factory cannot be changed after initialization!");
   }

   /*
    * (non-Javadoc)
    * 
    * @see com.sybase.EJB3Dao#getEntityManager(boolean)
    */
   public EntityManager getEntityManager(boolean createEntityManager) {
      return EJB3PersistenceManager.getEntityManager(createEntityManager,em);
   }

   /*
    * (non-Javadoc)
    * 
    * @see com.sybase.EJB3Dao#getEntityManager()
    */
   public EntityManager getEntityManager() {
      return getEntityManager(false);
   }

   /*
    * (non-Javadoc)
    * 
    * @see com.sybase.EJB3Dao#closePersistenceContext()
    */
   public void closePersistenceContext() throws DaoException {
      EJB3PersistenceManager.closeEntityManager();
   }

   /*
    * (non-Javadoc)
    * 
    * @see com.sybase.EJB3Dao#flushPersistenceContext()
    */
   public void flushPersistenceContext() throws DaoException {
      if (getEntityManager() != null)
         try {
            getEntityManager().flush();
         } catch (Exception e) {
            log.error("Fail to flush persistence context", e);
            throw new DaoException("Fail to flush persistence context", e);
         }
   }

   /*
    * (non-Javadoc)
    * 
    * @see com.sybase.EJB3Dao#clearPersistenceContext()
    */
   public void clearPersistenceContext() throws DaoException {
      if (getEntityManager() != null)
         try {
            getEntityManager().clear();
         } catch (Exception ex) {
            log.error("Fail to clear persistence context", ex);
            throw new DaoException("Fail to clear persistence context", ex);
         }
   }

   /*
    * (non-Javadoc)
    * 
    * @see com.sybase.EJB3Dao#beginTransaction()
    */
   public void beginTransaction() throws DaoException {
      EJB3PersistenceManager.beginTransaction();
   }

   /*
    * (non-Javadoc)
    * 
    * @see com.sybase.EJB3Dao#commitTransaction()
    */
   public void commitTransaction() throws DaoException {
      EJB3PersistenceManager.commitTransaction();
   }

   /*
    * (non-Javadoc)
    * 
    * @see com.sybase.EJB3Dao#rollbackTransaction()
    */
   public void rollbackTransaction() throws DaoException {
      EJB3PersistenceManager.rollbackTransaction();
   }

   /*
    * (non-Javadoc)
    * 
    * @see com.sybase.EJB3Dao#isAutoCommit()
    */
   public boolean isAutoCommit() {
      return this.autoCommit;
   }

   /*
    * (non-Javadoc)
    * 
    * @see com.sybase.EJB3Dao#setAutoCommit(boolean)
    */
   public void setAutoCommit(boolean autoCommit) {
      this.autoCommit = autoCommit;
   }

   /*
    * (non-Javadoc)
    * 
    * @see com.sybase.EJB3Dao#isAutoClosePersistenceContext()
    */
   public boolean isAutoClosePersistenceContext() {
      return this.autoCloseEntityManager;
   }

   /*
    * (non-Javadoc)
    * 
    * @see com.sybase.EJB3Dao#setAutoClosePersistenceContext(boolean)
    */
   public void setAutoClosePersistenceContext(boolean autoClose) {
      this.autoCloseEntityManager = autoClose;
   }

   /*
    * (non-Javadoc)
    * 
    * @see com.sybase.EJB3Dao#getConnection()
    */
   public Connection getConnection() {
      return null;
   }

   /*
    * (non-Javadoc)
    * 
    * @see com.sybase.EJB3Dao#closeConnection()
    */
   public void closeConnection() throws DaoException {
      if (getEntityManager() != null)
         try {
            getEntityManager().close();
         } catch (Exception ex) {
            log.error("Fail to close connection", ex);
            throw new DaoException("Fail to close connection", ex);
         } 
   }
   
   /*
    * (non-Javadoc)
    * 
    * @see com.sybase.EJB3Dao#save(java.lang.Object)
    */
   public void save(Object persistentObject) throws DaoException {
      EntityManager em = this.createEntityManager();
      try {         
         //beginTransaction();
         em.persist(persistentObject);
         /*if (autoCommit)
            commitTransaction();*/
      } catch (Exception ex) {
         rollbackTransaction();
         log.error("Fail to save persistentObject", ex);
         throw new DaoException("Fail to save persistentObject", ex);
      } finally {
         if (autoCloseEntityManager)
            closePersistenceContext();
      }
   }

   /*
    * (non-Javadoc)
    * 
    * @see com.sybase.EJB3Dao#update(java.lang.Object)
    */
   public Object update(Object persistentObject) throws DaoException {
      EntityManager em = createEntityManager();
      try {         
         //beginTransaction();
         Object object = em.merge(persistentObject);
         persistentObject = object;
/*         if (autoCommit)
            commitTransaction();*/
      } catch (Exception ex) {
         rollbackTransaction();
         log.error("Fail to update", ex);
         throw new DaoException("Fail to update", ex);
      } finally {
         if (autoCloseEntityManager)
            closePersistenceContext();
      }
      return persistentObject;
   }

   /*
    * (non-Javadoc)
    * 
    * @see com.sybase.EJB3Dao#delete(java.lang.Object)
    */
   public void delete(Object persistentObject) throws DaoException {
      EntityManager em = createEntityManager();
      try {         
         //beginTransaction();
         Object object = em.merge(persistentObject);
         em.remove(object);
/*         if (autoCommit)
            commitTransaction();*/
      } catch (Exception ex) {
         rollbackTransaction();
         log.error("Fail to delete", ex);
         throw new DaoException("Fail tlo delete", ex);
      } finally {
         if (autoCloseEntityManager)
            closePersistenceContext();
      }
   }   

   /*
    * (non-Javadoc)
    * 
    * @see com.sybase.EJB3Dao#findBySQLQuery(java.lang.String,
    *      java.lang.String, java.lang.Class)
    */
   public List findBySQLQuery(String sqlQuery, Class clazz)
         throws DaoException {
      List result = new ArrayList();
      EntityManager em = this.createEntityManager();
      try {
         result = em.createNativeQuery(sqlQuery, clazz).getResultList();
      } catch (Exception ex) {
         log.error("Fail to execute query", ex);
         throw new DaoException("Fail to execute query", ex);
      } finally {
         if (autoCloseEntityManager)
            closePersistenceContext();
      }
      return result;
   }

   /*
    * (non-Javadoc)
    * 
    * @see com.sybase.EJB3Dao#findByEJBQuery(java.lang.String)
    */
   public List findByEJBQuery(String ejbQuery) throws DaoException {
      List result = new ArrayList();
      EntityManager em = this.createEntityManager();
      try {
         result = em.createQuery(ejbQuery).getResultList();
      } catch (Exception ex) {
         log.error("Fail to execute query", ex);
         throw new DaoException("Fail to execute query", ex);
      } finally {
         if (autoCloseEntityManager)
         closePersistenceContext();
      }
      return result;
   }

   /*
    * (non-Javadoc)
    * 
    * @see com.sybase.EJB3Dao#findByEJBQuery(java.lang.String)
    */
   public List findByEJBQuery(String ejbQuery, int firstResult, int maxResult)
         throws DaoException {
      List result = new ArrayList();
      EntityManager em = this.createEntityManager();
      try {
         result = em.createQuery(ejbQuery).setFirstResult(firstResult).setMaxResults(maxResult).getResultList();
      } catch (Exception ex) {
         log.error("Fail to execute query", ex);
         throw new DaoException("Fail to execute query", ex);
      } finally {
         if (autoCloseEntityManager)
         closePersistenceContext();
      }
      return result;
   }

   /*
    * (non-Javadoc)
    * 
    * @see com.sybase.EJB3Dao#findByNamedParam(java.lang.String,
    *      java.lang.String,java.lang.Object)
    */
   public List findByNamedParam(String queryString, String paramName,
         Object value) throws DaoException {
      return findByNamedParam(queryString, new String[] { paramName },
         new Object[] { value });
   }

   /*
    * (non-Javadoc)
    * 
    * @see com.sybase.EJB3Dao#findByNamedParam(java.lang.String,
    *      java.lang.String, java.lang.Object, int, int)
    */
    public List findByNamedParam(String queryString, String paramName,
          Object value, int firstResult, int maxResult) throws DaoException {
      return findByNamedParam(queryString, new String[] { paramName },
         new Object[] { value }, firstResult, maxResult);
   }

   /*
    * (non-Javadoc)
    * 
    * @see com.sybase.EJB3Dao#findByNamedParam(java.lang.String,
    *      java.lang.String[], java.lang.Object[])
    */
   public List findByNamedParam(final String queryString,
         final String[] paramNames, final Object[] values)
         throws DaoException {
         
      if (paramNames != null && values != null && paramNames.length != values.length) {
         throw new IllegalArgumentException(
            "Length of paramNames array must match length of values array");
      }
      List result = new ArrayList();
      EntityManager em = this.createEntityManager();
      try {
         Query queryObject = em.createQuery(queryString);
         for (int i = 0; i < values.length; i++) {
            applyNamedParameterToQuery(queryObject, paramNames[i], values[i]);
         }
         result = queryObject.getResultList();
      } catch (Exception ex) {
         log.error("Fail to find", ex);
         throw new DaoException("Fail to find", ex);
      } finally {
         if (autoCloseEntityManager)
            closePersistenceContext();
      }
      return result;
   }

   /*
    * (non-Javadoc)
    * 
    * @see com.sybase.EJB3Dao#findByNamedParam(java.lang.String,java.lang.String[],
    *      java.lang.Object[], int, int)
    */
   public List findByNamedParam(final String queryString,
         final String[] paramNames, final Object[] values,
         final int firstResult, final int maxResult) throws DaoException {
      
      if (paramNames != null && values != null && paramNames.length != values.length) {
         throw new IllegalArgumentException(
            "Length of paramNames array must match length of values array");
      }
      List result = new ArrayList();
      EntityManager em = this.createEntityManager();
      try {
         Query queryObject = em.createQuery(queryString);
         queryObject.setFirstResult(firstResult);
         queryObject.setMaxResults(maxResult);         
         for (int i = 0; i < values.length; i++) {
            applyNamedParameterToQuery(queryObject, paramNames[i],	values[i]);
         }
         result = queryObject.getResultList();         
      } catch (Exception ex) {
         log.error("Fail to find by named param");
         throw new DaoException("Fail to find by named param", ex);
      } finally {
         if (autoCloseEntityManager)
            closePersistenceContext();
      }
      return result;
   }

   /*
    * (non-Javadoc)
    * 
    * @see com.sybase.EJB3Dao#findByNamedQuery(java.lang.String)
    */
   public List findByNamedQuery(String queryName) throws DaoException {
      return findByNamedQuery(queryName, (Object[]) null);
   }

   /*
    * (non-Javadoc)
    * 
    * @see com.sybase.EJB3Dao#findByNamedQuery(java.lang.String,
    *      java.lang.Object)
    */
   public List findByNamedQuery(String queryName, Object value)
         throws DaoException {
      return findByNamedQuery(queryName, new Object[] { value });
   }

   /*
    * (non-Javadoc)
    * 
    * @see com.sybase.EJB3Dao#findByNamedQuery(java.lang.String,
    *      java.lang.Object[])
    */
   public List findByNamedQuery(final String queryName, final Object[] values)
         throws DaoException {
      EntityManager em = this.createEntityManager();
      List result = new ArrayList();
      try {
         Query queryObject = em.createNamedQuery(queryName);
         if (values != null) {
            for (int i = 0; i < values.length; i++) {
               queryObject.setParameter(i, values[i]);
            }
         }
         result = queryObject.getResultList();
      } catch (Exception ex) {
         log.error("Fail to find by Named query", ex);
         throw new DaoException("Fail to find by Named query", ex);
      } finally {
         if (autoCloseEntityManager)
         closePersistenceContext();
      }
      return result;
   }

   /*
    * (non-Javadoc)
    * 
    * @see com.sybase.EJB3Dao#findByNamedQueryAndNamedParam(java.lang.String,
    *      java.lang.String, java.lang.Object)
    */
   public List findByNamedQueryAndNamedParam(String queryName,
         String paramName, Object value) throws DaoException {
      return findByNamedQueryAndNamedParam(queryName,
         new String[] { paramName }, new Object[] { value });
   }

   /*
    * (non-Javadoc)
    * 
    * @see com.sybase.EJB3Dao#findByNamedQueryAndNamedParam(java.lang.String,
    *      java.lang.String[], java.lang.Object[])
    */
   public List findByNamedQueryAndNamedParam(final String queryName,
         final String[] paramNames, final Object[] values)
         throws DaoException {
      if (paramNames != null && values != null
            && paramNames.length != values.length) {
         throw new IllegalArgumentException(
            "Length of paramNames array must match length of values array");
      }
      List result = new ArrayList();
      EntityManager em = this.createEntityManager();
      try {
         Query queryObject = em.createNamedQuery(queryName);
         for (int i = 0; i < values.length; i++) {
            applyNamedParameterToQuery(queryObject, paramNames[i],	values[i]);
         }
         result = queryObject.getResultList();
      } catch (Exception ex) {
         log.error("Fail to find by Named query and Named Param", ex);
         throw new DaoException(
            "Fail to find by Named query and Named Param", ex);
      } finally {
         if (autoCloseEntityManager)
            closePersistenceContext();
      }
      return result;
   }

   /**
    * Apply parameter value to query parameter
    * 
    * @param queryObject
    * @param paramName
    * @param value
    * @throws Exception
    */
   protected void applyNamedParameterToQuery(Query queryObject,
         String paramName, Object value) throws Exception {
      if (value instanceof Collection) {
         queryObject.setParameter(paramName, (Collection) value);
      } else if (value instanceof Object[]) {
         queryObject.setParameter(paramName, (Object[]) value);
      } else {
         queryObject.setParameter(paramName, value);
      }
   }   

   /*
    * (non-Javadoc)
    * 
    * @see com.sybase.EJB3Dao#find(java.lang.String,
    *      int, int)
    */
   public List find(String queryString, int firstResult, int maxResult)
         throws DaoException {
      return find(queryString, (Object[]) null, firstResult, maxResult);
   }

   /*
    * (non-Javadoc)
    * 
    * @see com.sybase.EJB3Dao#find(java.lang.String)
    */
   public List find(String queryString) throws DaoException {
      return find(queryString, (Object[]) null);
   }

   /*
    * (non-Javadoc)
    * 
    * @see com.sybase.orm.hibernate.dao.HibernateDao#find(java.lang.String,
    *      java.lang.Object)
    */
   public List find(String queryString, Object value) throws DaoException {
      return find(queryString, new Object[] { value });
   }

   /*
    * (non-Javadoc)
    * 
    * @see com.sybase.EJB3Dao#find(java.lang.String,
    *      java.lang.Object[])
    */
   public List find(final String queryString, final Object[] values)
         throws DaoException {
      List result = new ArrayList();
      EntityManager em = this.createEntityManager();
      try {
         Query queryObject = em.createQuery(queryString);
         if (values != null) {
            for (int i = 0; i < values.length; i++) {
               queryObject.setParameter(i, values[i]);
            }
         }
         result = queryObject.getResultList();
      } catch (Exception ex) {
         log.error("Fail to find by query string", ex);
         throw new DaoException("Fail to find by query string", ex);
      } finally {
         if (autoCloseEntityManager)
            closePersistenceContext();
      }
      return result;
   }

   /*
    * (non-Javadoc)
    * 
    * @see com.sybase.EJB3Dao#find(java.lang.String,
    *      java.lang.Object[], int, int)
    */
   public List find(final String queryString, final Object[] values,
         final int firstResult, final int maxResult) throws DaoException {
      List result = new ArrayList();
      EntityManager em = this.createEntityManager();
      try {
         Query queryObject = em.createQuery(queryString).setFirstResult(
            firstResult).setMaxResults(maxResult);
         if (values != null) {
            for (int i = 0; i < values.length; i++) {
               queryObject.setParameter(i, values[i]);
            }
         }
         result = queryObject.getResultList();
      } catch (Exception ex) {
         log.error("Fail to find by query string", ex);
         throw new DaoException("Fail to find by query string", ex);
      } finally {
         if (autoCloseEntityManager)
            closePersistenceContext();
      }
      return result;
   }

   /*
    * Get entity manager associated with current thread. Create new one if not exist.
    * 
    * @return entity manager
    */
   private EntityManager createEntityManager() {
      return getEntityManager(true);
   }
   
   /*
    * (non-Javadoc)
    * 
    * @see com.sybase.EJB3Dao#find(java.lang.Class,
    *      java.lang.Object)
    */
   public <T> T find(Class<T> entityClass, Object primaryKey) {
      EntityManager em = this.createEntityManager();
      try {
         return em.find(entityClass, primaryKey);
      } catch (Exception ex) {
         log.error("Fail to find by query string", ex);
         throw new DaoException("Fail to find by query string", ex);
      } finally {
         if (autoCloseEntityManager)
            closePersistenceContext();
      }
   }
   
   /*
    * (non-Javadoc)
    * 
    * @see com.sybase.EJB3Dao#getReference(java.lang.Class,
    *      java.lang.Object)
    */
   public <T> T getReference(Class<T> entityClass, Object primaryKey) {
      EntityManager em = this.createEntityManager();
      try {
         return em.getReference(entityClass, primaryKey);
      } catch (Exception ex) {
         log.error("Fail to find by query string", ex);
         throw new DaoException("Fail to find by query string", ex);
      } finally {
         if (autoCloseEntityManager)
         closePersistenceContext();
      }
   }
}