/***********************************************************************
 * Module:  UserBasicInfoDaoImpl.java
 * Author:  mhd.mirali
 * Purpose: Defines data access implementation class for class UserBasicInfo
 ***********************************************************************/
 
package ir.fanrp.model.userProfile.daoimpl.ejb3;

import java.util.*;
import java.io.Serializable;
import ir.fanrp.model.userProfile.UserBasicInfo;
import ir.fanrp.model.userProfile.UserBasicInfoPK;
import com.sybase.orm.dao.Dao;
import com.sybase.orm.dao.DaoException;
import ir.fanrp.model.userProfile.dao.UserBasicInfoDao;
import com.sybase.orm.ejb3.dao.EJB3DaoImpl;
import org.springframework.stereotype.Repository;

@Repository
public class UserBasicInfoDaoImpl extends EJB3DaoImpl implements UserBasicInfoDao {

   private static final String GET_ALL_QUERY_STRING = "Select o from UserBasicInfo o";

   public UserBasicInfo save(UserBasicInfo userBasicInfoObject) throws DaoException{
       super.save(userBasicInfoObject);
       return userBasicInfoObject;
   }
   
   public UserBasicInfo load(UserBasicInfoPK pk) throws DaoException{
         return super.find(UserBasicInfo.class, pk.getId());
   }
   
   public void delete(UserBasicInfo userBasicInfoObject) throws DaoException{
      super.delete(userBasicInfoObject);
   }


   
   public UserBasicInfo update(UserBasicInfo userBasicInfoObject) throws DaoException{
      super.update(userBasicInfoObject);
       return userBasicInfoObject;
   }



   public List<UserBasicInfo> getUserBasicInfoList() throws DaoException{   
      return super.findByEJBQuery(GET_ALL_QUERY_STRING);
   }
   
   public List<UserBasicInfo> getUserBasicInfoList(int firstResult, int maxResult){
      return super.findByEJBQuery(GET_ALL_QUERY_STRING, firstResult, maxResult);
   }
   
}