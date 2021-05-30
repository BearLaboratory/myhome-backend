package pro.dengyi.myhome.servicebackend.dto;


import lombok.Data;
import pro.dengyi.myhome.myhomemodel.business.backend.User;

/**
 * @author DengYi
 * @version v1.0
 */
@Data
public class UserDto extends User {
    private String roleName;
}
