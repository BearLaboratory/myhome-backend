package pro.dengyi.myhome.myhomemodel.business.backend;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 用户角色中间
 *
 * @author DengYi
 * @version v1.0
 */
@Data
@TableName(value = "sys_user_role_middle")
public class UserRoleMiddle {

    private String id;
    private String userId;
    private String roleId;
}
