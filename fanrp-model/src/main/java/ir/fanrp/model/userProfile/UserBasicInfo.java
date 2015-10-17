/***********************************************************************
 * Module:  UserBasicInfo.java
 * Author:  mhd.mirali
 * Purpose: Defines the Class UserBasicInfo
 ***********************************************************************/

package ir.fanrp.model.userProfile;

import java.util.*;
import javax.persistence.*;

/** @pdOid 1ae3c62f-37d1-45ab-bc16-99acc354f4f9 */
@Entity(name="UserBasicInfo")
@Table(name="UserBasicInfo")
public class UserBasicInfo implements java.io.Serializable {
   /** @pdOid 320f19b9-daa5-46ef-84e7-34b3ce27dfb6 */
   private Long id;
   /** @pdOid 0d297e6b-f7f5-4094-ae32-bd9c8f0c4a64 */
   private String firstName;
   /** @pdOid 5fa0d9ff-7d15-4f0b-a343-ba000cf18e37 */
   private String lastName;
   /** @pdOid de93bc0a-9b14-4ccb-b855-5d802ce57272 */
   private String nationalId;
   /** @pdOid 9d4ad577-7ae2-4f7d-9adf-56bfc08b981f */
   private Date birthDate;
   /** @pdOid 7a18405f-a228-4bb4-b15c-414b0dae3985 */
   private Long lastStatus;
   
   /**
    * Empty constructor which is required by EJB 3.0 spec.
    *
    */
   public UserBasicInfo() {
      // TODO Add your own initialization code here.
   }
   
   /**
    * Get value of id
    *
    * @return id 
    */
   @Id
   @Column(name="ID", nullable=false, insertable=true, updatable=true)
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
   
   /**
    * Get value of firstName
    *
    * @return firstName 
    */
   @Basic(optional=true)
   @Column(name="FIRST_NAME", insertable=true, updatable=true, length=254)
   public String getFirstName()
   {
      return firstName;
   }
   
   /**
    * Set value of firstName
    *
    * @param newFirstName 
    */
   public void setFirstName(String newFirstName)
   {
      this.firstName = newFirstName;
   }
   
   /**
    * Get value of lastName
    *
    * @return lastName 
    */
   @Basic(optional=true)
   @Column(name="LAST_NAME", insertable=true, updatable=true, length=254)
   public String getLastName()
   {
      return lastName;
   }
   
   /**
    * Set value of lastName
    *
    * @param newLastName 
    */
   public void setLastName(String newLastName)
   {
      this.lastName = newLastName;
   }
   
   /**
    * Get value of nationalId
    *
    * @return nationalId 
    */
   @Basic(optional=true)
   @Column(name="NATIONAL_ID", insertable=true, updatable=true, length=254)
   public String getNationalId()
   {
      return nationalId;
   }
   
   /**
    * Set value of nationalId
    *
    * @param newNationalId 
    */
   public void setNationalId(String newNationalId)
   {
      this.nationalId = newNationalId;
   }
   
   /**
    * Get value of birthDate
    *
    * @return birthDate 
    */
   @Basic(optional=true)
   @Temporal(TemporalType.DATE)
   @Column(name="BIRTH_DATE", insertable=true, updatable=true)
   public Date getBirthDate()
   {
      return birthDate;
   }
   
   /**
    * Set value of birthDate
    *
    * @param newBirthDate 
    */
   public void setBirthDate(Date newBirthDate)
   {
      this.birthDate = newBirthDate;
   }
   
   /**
    * Get value of lastStatus
    *
    * @return lastStatus 
    */
   @Basic(optional=true)
   @Column(name="LAST_STATUS", insertable=true, updatable=true)
   public Long getLastStatus()
   {
      return lastStatus;
   }
   
   /**
    * Set value of lastStatus
    *
    * @param newLastStatus 
    */
   public void setLastStatus(Long newLastStatus)
   {
      this.lastStatus = newLastStatus;
   }
   
   /**
    * Create a new userBasicInfoPK instance by primary identifier attribute(s)
    *
    * @return userBasicInfoPK object
    */
   @Transient
   public UserBasicInfoPK getUserBasicInfoPK() {
      return new UserBasicInfoPK(id);
   }
   
   /**
    * Set value(s) of primary identifier attribute(s) by userBasicInfoPK instance specified.
    * 
    * @param pk
    */
   public void setUserBasicInfoPK(UserBasicInfoPK pk) {
      if (pk != null) {
         this.id = pk.getId();
      }
   }
   
   /* (non-Javadoc)
    * @see java.lang.Object#equals(java.lang.Object)
    */
   public boolean equals(Object other) {
      if (other == null)
         return false;
      
      if (other == this)
         return true;   
   
      if (!(other instanceof UserBasicInfo))
         return false;
   
      UserBasicInfo cast = (UserBasicInfo) other;
      if (this.id == null ? cast.getId() != this.id : !this.id.equals( cast.getId()))
         return false;
   
      if (this.firstName == null ? cast.getFirstName() != this.firstName : !this.firstName.equals( cast.getFirstName()))
         return false;
   
      if (this.lastName == null ? cast.getLastName() != this.lastName : !this.lastName.equals( cast.getLastName()))
         return false;
   
      if (this.nationalId == null ? cast.getNationalId() != this.nationalId : !this.nationalId.equals( cast.getNationalId()))
         return false;
   
      if (this.birthDate == null ? cast.getBirthDate() != this.birthDate : !(com.sybase.orm.util.Util.compareDate(this.birthDate,  cast.getBirthDate(), java.util.Calendar.SECOND) == 0))
         return false;
   
      if (this.lastStatus == null ? cast.getLastStatus() != this.lastStatus : !this.lastStatus.equals( cast.getLastStatus()))
         return false;
   
      return true;
   }
   
   /* (non-Javadoc)
    * @see java.lang.Object#hashCode()
    */
   public int hashCode() {
      int hashCode = 0;
      if (this.id != null) 
         hashCode = 29 * hashCode + id.hashCode();
      if (this.firstName != null) 
         hashCode = 29 * hashCode + firstName.hashCode();
      if (this.lastName != null) 
         hashCode = 29 * hashCode + lastName.hashCode();
      if (this.nationalId != null) 
         hashCode = 29 * hashCode + nationalId.hashCode();
      if (this.birthDate != null) 
         hashCode = 29 * hashCode + birthDate.hashCode();
      if (this.lastStatus != null) 
         hashCode = 29 * hashCode + lastStatus.hashCode();
      return hashCode;
   }
   
   /* (non-Javadoc)
    * @see java.lang.Object#toString()
    */
   public String toString() {
      StringBuffer ret = new StringBuffer();
      ret.append( "ir.fanrp.model.userProfile.UserBasicInfo: " );
      ret.append( "id='" + id + "'");
      ret.append( "firstName='" + firstName + "'");
      ret.append( "lastName='" + lastName + "'");
      ret.append( "nationalId='" + nationalId + "'");
      ret.append( "birthDate='" + birthDate + "'");
      ret.append( "lastStatus='" + lastStatus + "'");
      return ret.toString();
   }

}