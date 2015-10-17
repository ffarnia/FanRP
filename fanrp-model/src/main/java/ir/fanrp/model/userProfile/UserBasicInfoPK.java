/***********************************************************************
 * Module:  UserBasicInfoPK.java
 * Author:  mhd.mirali
 * Purpose: Defines priamry key class for class UserBasicInfo
 ***********************************************************************/
 
package ir.fanrp.model.userProfile;

import java.util.*;
import javax.persistence.*;

/**
 * Generated primary key class for class UserBasicInfo. It is used to simplify query-by-primary-key operation.
 * 
 */
public class UserBasicInfoPK implements java.io.Serializable{
   /** @pdOid 320f19b9-daa5-46ef-84e7-34b3ce27dfb6 */
   private Long id;
   
   /**
    * Empty constructor
    *
    */
   public UserBasicInfoPK(){
      // TODO Add your own initialization code here. 
   }
   
   /**
    * Constructor with parameters
    *  
    * @param id parameter to set value of field id
    */
   public UserBasicInfoPK(Long id) {
      this.id = id;
   }

   /**
    * Get value of id
    *
    * @return id 
    */
   public Long getId()
   {
      return id;
   }
   
   /**
    * Set value of id
    *
    * @param newId 
    */
   public void setId(Long newId)
   {
      this.id = newId;
   }

   /* (non-Javadoc)
    * @see java.lang.Object#equals(java.lang.Object)
    */
   public boolean equals(Object other) {
   
      if (other == null) {
         return false;
      }
      
      if (other == this) {
         return true;
      }
      
      if (! (other.getClass() ==  getClass()))
         return false;
   
      UserBasicInfoPK cast = (UserBasicInfoPK) other;
   
      if (this.id == null ? cast.getId() != this.id : !this.id.equals( cast.getId()))
         return false;
   
      return true;
   }

   /* (non-Javadoc)
    * @see java.lang.Object#hashCode()
    */
   public int hashCode() {
      int _hashCode = 0;
      if (this.id != null) 
         _hashCode = 29 * _hashCode + id.hashCode();
      return _hashCode;
   }

   /* (non-Javadoc)
    * @see java.lang.Object#toString()
    */
   public String toString()
   {
      StringBuffer ret = new StringBuffer();
      ret.append( "ir.fanrp.model.userProfile.UserBasicInfoPK: " );
      ret.append( "id='" + id + "'");
      return ret.toString();
   }
}