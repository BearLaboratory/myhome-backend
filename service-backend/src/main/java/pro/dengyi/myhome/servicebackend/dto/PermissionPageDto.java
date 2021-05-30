package pro.dengyi.myhome.servicebackend.dto;


import lombok.Data;
import pro.dengyi.myhome.myhomemodel.business.backend.Permission;

/**
 * 权限dto
 *
 * @author DengYi
 * @version v1.0
 */
@Data
public class PermissionPageDto extends Permission {
    private Boolean hasChildren;
}
