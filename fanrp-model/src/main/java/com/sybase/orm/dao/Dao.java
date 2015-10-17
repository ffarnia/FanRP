/***********************************************************************
 * Module:  Dao.java
 * Author:  a.gholaminejad
 * Purpose: Defines common interface for data access
 ***********************************************************************/

package com.sybase.orm.dao;

import java.sql.Connection;

/**
 * Interface that defines common methods for data access.
 *
 */
public interface Dao{

   /**
    * Get connection used
    *
    * @return connection
    */
   public Connection getConnection();
   
   /**
    * Close connection used
    */ 
   public void closeConnection() throws DaoException;
   
   /**
    * Begin a new transaction
    */ 
   public void beginTransaction() throws DaoException;
   
   /**
    * Commit current transaction
    *
    */
   public void commitTransaction() throws DaoException;
   
   /**
    * Rollback current transaction
    */
   public void rollbackTransaction() throws DaoException;
   
   /**
    * Check if auto commit is set
    *
    * @return Auto commit flag
    */
   public boolean isAutoCommit();
   
   /**
    * Set auto commit flag
    *
    * @param autoCommit
    */
   public void setAutoCommit(boolean autoCommit);

   /**
    * Check if persistence context is auto closed
    *
    * @return auto close persistence context flag
    */
   public boolean isAutoClosePersistenceContext();

   /**
    * Set persistence context auto close flag
    *
    * @param autoClose
    */
   public void setAutoClosePersistenceContext(boolean autoClose);
   
   /**
    * Close persistence context, releasing connection used.
    *
    */
   public void closePersistenceContext() throws DaoException;
   
   /**
    * Flush persistence context, making changes effective to database.
    *
    */
   public void flushPersistenceContext() throws DaoException;
   
   /**
    * Clear persistence context, discarding cached persistent objects.
    *
    */
   public void clearPersistenceContext() throws DaoException;
}