/***********************************************************************
 * Module:  UserBasicInfoDao.java
 * Author:  mhd.mirali
 * Purpose: Defines data access interface for class UserBasicInfo
 ***********************************************************************/
 
package ir.fanrp.model.userProfile.dao;

import java.util.*;
import java.io.Serializable;
import ir.fanrp.model.userProfile.UserBasicInfo;
import ir.fanrp.model.userProfile.UserBasicInfoPK;
import com.sybase.orm.dao.Dao;
import com.sybase.orm.dao.DaoException;


public interface UserBasicInfoDao extends Dao {
   
   public UserBasicInfo save(UserBasicInfo userBasicInfoObject) throws DaoException;
  
   public UserBasicInfo load(UserBasicInfoPK pk) throws DaoException;

   public void delete(UserBasicInfo userBasicInfoObject) throws DaoException;



   public UserBasicInfo update(UserBasicInfo userBasicInfoObject) throws DaoException;

   public List<UserBasicInfo> getUserBasicInfoList() throws DaoException;
   
   public List<UserBasicInfo> getUserBasicInfoList(int firstResult, int maxResult);
   

}