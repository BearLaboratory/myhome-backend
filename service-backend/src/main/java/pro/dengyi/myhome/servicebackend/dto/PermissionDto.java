package pro.dengyi.myhome.servicebackend.dto;


import lombok.Data;
import pro.dengyi.myhome.myhomemodel.business.backend.Permission;

import java.util.List;

/**
 * 权限dto
 *
 * @author DengYi
 * @version v1.0
 */
@Data
public class PermissionDto {
    private List<Permission> buttons;
    private List<PermissionMenuDto> menus;
}
