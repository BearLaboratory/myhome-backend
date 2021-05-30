package pro.dengyi.myhome.servicebackend.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import pro.dengyi.myhome.myhomemodel.business.backend.Permission;
import pro.dengyi.myhome.servicebackend.dto.PermissionMenuDto;
import pro.dengyi.myhome.servicebackend.dto.PermissionPageDto;

import java.util.List;

/**
 * @author DengYi
 * @version v1.0
 */
@Repository
public interface PermissionDao extends BaseMapper<Permission> {
    IPage<PermissionPageDto> selectCustomPage(IPage<PermissionPageDto> pageParam);

    List<PermissionPageDto> selectByParentId(@Param("parentId") String parentId);

    List<PermissionMenuDto> selectFirstMenuPermissionForSuperAdmin();

    List<PermissionMenuDto> selectChildMenuPermissionByParentId(@Param("parentId") String id);

    List<Permission> selectFirstButtonPermissionForSuperAdmin();

    List<PermissionMenuDto> selectFirstMenuPermissionByUserId(String userId);

    List<Permission> selectFirstButtonPermissionByUserId(String userId);

    List<Permission> selectChildButtonPermissionByParentId(String id);

    List<PermissionMenuDto> selectAllSubByParentId(@Param("parentId") String parentId);
}
