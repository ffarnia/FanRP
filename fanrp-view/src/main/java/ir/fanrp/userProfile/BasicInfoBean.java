package ir.fanrp.userProfile;

import ir.fanrp.model.userProfile.Enumeration.GenderEnum;
import ir.fanrp.model.userProfile.UserBasicInfo;
import ir.fanrp.service.userProfile.BasicInfoBS;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.model.SelectItem;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by vzf on 10/8/2015.
 */

@ManagedBean(name = "basicInfoBean")
@RequestScoped
public class BasicInfoBean {

    private UserBasicInfo userBasicInfo;
    private List gendertypeList;

    @ManagedProperty("#{basicInfoBS}")
    private BasicInfoBS basicInfoBS;

    public void setBasicInfoBS(BasicInfoBS basicInfoBS) {
        this.basicInfoBS = basicInfoBS;
    }

    @PostConstruct
    public void init() {
        userBasicInfo = new UserBasicInfo();
    }

    public void saveBasicInfo() {
        basicInfoBS.saveBasicInfo(userBasicInfo);
    }

    public UserBasicInfo getUserBasicInfo() {
        return userBasicInfo;
    }

    public void setUserBasicInfo(UserBasicInfo userBasicInfo) {
        this.userBasicInfo = userBasicInfo;
    }

    public List getGendertypeList() {
        gendertypeList = new ArrayList();
        for (GenderEnum genderEnum:GenderEnum.getItems()){
            gendertypeList.add(new SelectItem(genderEnum.getValue(),genderEnum.getLabel()));
        }

        return gendertypeList;
    }

    public void setGendertypeList(List gendertypeList) {
        this.gendertypeList = gendertypeList;
    }
}
