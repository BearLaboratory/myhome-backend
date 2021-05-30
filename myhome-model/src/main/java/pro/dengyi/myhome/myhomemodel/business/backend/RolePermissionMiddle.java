package pro.dengyi.myhome.myhomemodel.business.backend;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 角色权限中间
 *
 * @author DengYi
 * @version v1.0
 */
@Data
@TableName(value = "sys_role_permission_middle")
public class RolePermissionMiddle {

    private String id;
    private String roleId;
    private String permissionId;
}
