/***********************************************************************
 * Module:  EJB3PersistenceManager.java
 * Author:  a.gholaminejad
 * Purpose: Defines class for EJB 3 persistence management
 ***********************************************************************/

package com.sybase.orm.ejb3;

import com.sybase.orm.dao.DaoException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

/**
 * Class used for EJB 3 persistence management.
 *
 */

public class EJB3PersistenceManager {



   /*private static final EntityManagerFactory entityManagerFactory;*/

   private static final ThreadLocal<EntityManager> threadEntityManager = new ThreadLocal<EntityManager>();

   private static Log log = LogFactory.getLog(EJB3PersistenceManager.class);

   /*static {
      try {
         entityManagerFactory = Persistence.createEntityManagerFactory("bss", new HashMap());
      } catch (Throwable ex) {
         ex.printStackTrace();
         log.error("Initial EntityManagerFactory creation failed.", ex);
         throw new DaoException("Initial EntityManagerFactory creation failed.", ex);
      }
   }*/

   /**
    * Private empty constructor. Cannot be used to create instance. Call static methods directly.
    *
    */
   private EJB3PersistenceManager() {

   }


   public static EntityManager getEntityManager(boolean createEntityManager , EntityManager em1) throws DaoException {
      EntityManager em = (EntityManager) threadEntityManager.get();
      if (createEntityManager) {
         if (em == null) {
            try {
               //em = entityManagerFactory.createEntityManager();
               threadEntityManager.set(em1);
               em=em1;
            } catch (Exception ex) {
               log.error("Fail to create entity manager", ex);
               throw new DaoException("Fail to create entity manager", ex);
            }
         }
      }
      return em;
   }

   /**
    * Associate entity manager specified with current thread. Return old one if exists.
    *
    * @param entityManager specified entity manager
    * @return old entity manager if any
    */
   public static EntityManager setEntityManager(EntityManager entityManager) {
      EntityManager em = (EntityManager) threadEntityManager.get();
      threadEntityManager.set(entityManager);
      return em;
   }

   /**
    * Close entity manager associated with current thread if exists.
    *
    * @throws DaoException
    */
   public static void closeEntityManager() throws DaoException {
      EntityManager em = (EntityManager) threadEntityManager.get();
      threadEntityManager.set(null);
      try {
         if (em != null && em.isOpen())
            em.close();
      } catch (Exception ex) {
         log.error("Fail to close entity manager", ex);
         throw new DaoException("Fail to close entity manager", ex);
      }
   }

   /**
    * Begin transaction from entity manager associated with current thread. Create a 
    * new entity manager if none found.
    *
    * @throws DaoException
    */
   public static void beginTransaction() throws DaoException {
      EntityTransaction tx = getEntityManager(true,null).getTransaction();
      try {
         if (!tx.isActive())
            tx.begin();
      } catch (Exception ex) {
         log.error("Fail to begin transaction", ex);
         throw new DaoException("Fail to begin transaction", ex);
      }
   }

   /**
    * Commit transaction associated with current thread.
    *
    * @throws DaoException
    */
   public static void commitTransaction() throws DaoException {
      EntityManager em = getEntityManager(false,null);
      EntityTransaction tx = (em != null)?em.getTransaction():null;
      try {
         if (tx != null && tx.isActive())
            tx.commit();
      } catch (Exception ex) {
         rollbackTransaction();
         log.error("Fail to commit transaction", ex);
         throw new DaoException("Fail to commit transaction", ex);
      }
   }

   /**
    * Rollback transaction associated with current thread. Close entity manager in the end.
    *
    * @throws DaoException
    */
   public static void rollbackTransaction() throws DaoException {
      EntityManager em = getEntityManager(false,null);
      EntityTransaction tx = (em != null)?em.getTransaction():null;
      try {
         if (tx != null && tx.isActive())
            tx.rollback();
      } catch (Exception ex) {
         log.error("Fail to rollback transaction", ex);
         throw new DaoException("Fail to rollback transaction", ex);
      } finally {
         closeEntityManager();
      }
   }

   /**
    * Get entity manager factory.
    *
    * @return
    */
/*   public static EntityManagerFactory getEntityManagerFactory() {
      return entityManagerFactory;
   }*/
}