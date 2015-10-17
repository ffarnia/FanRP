package ir.fanrp.service.userProfile;

import ir.fanrp.model.userProfile.UserBasicInfo;
import ir.fanrp.model.userProfile.dao.UserBasicInfoDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by vzf on 10/11/2015.
 */

@Service("basicInfoBS")
public class BasicInfoBS {

    @Autowired
    private UserBasicInfoDao userBasicInfoDao;


    @Transactional
    public UserBasicInfo saveBasicInfo(UserBasicInfo userBasicInfo){

        if (userBasicInfo.getId() == null){
            userBasicInfoDao.save(userBasicInfo);
        }else{
            userBasicInfoDao.update(userBasicInfo);
        }
        return  userBasicInfo;
    }

}
