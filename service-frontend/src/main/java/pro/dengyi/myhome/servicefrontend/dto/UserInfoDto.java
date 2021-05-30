package pro.dengyi.myhome.servicefrontend.dto;

import lombok.Data;
import pro.dengyi.myhome.myhomemodel.business.Family;
import pro.dengyi.myhome.myhomemodel.business.User;


/**
 * @author BLab
 */
@Data
public class UserInfoDto {
    private User user;
    private Family family;
    private String selectedFloorId;
    private Boolean isHolder;

}
