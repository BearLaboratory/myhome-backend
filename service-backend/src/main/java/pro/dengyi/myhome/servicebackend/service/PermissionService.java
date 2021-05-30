package pro.dengyi.myhome.servicebackend.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import pro.dengyi.myhome.myhomemodel.business.backend.Permission;
import pro.dengyi.myhome.servicebackend.dto.PermissionMenuDto;
import pro.dengyi.myhome.servicebackend.dto.PermissionPageDto;
import pro.dengyi.myhome.servicebackend.vo.PermPageVo;

import java.util.List;

/**
 * @author DengYi
 * @version v1.0
 */
public interface PermissionService {
    IPage<PermissionPageDto> page(PermPageVo vo);

    /**
     * 根据是否存在ID来判断是新增还是修改
     *
     * @param permission
     */
    void addOrUpdate(Permission permission);

    List<PermissionPageDto> getByParentId(String parentId);

    List<PermissionMenuDto> totalPermissionTree();

}
