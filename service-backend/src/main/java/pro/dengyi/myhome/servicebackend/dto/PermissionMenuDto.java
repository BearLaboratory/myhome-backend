package pro.dengyi.myhome.servicebackend.dto;


import lombok.Data;
import pro.dengyi.myhome.myhomemodel.business.backend.Permission;

import java.util.List;

/**
 * 权限树dto
 *
 * @author DengYi
 * @version v1.0
 */
@Data
public class PermissionMenuDto extends Permission {
    private List<PermissionMenuDto> children;
}
