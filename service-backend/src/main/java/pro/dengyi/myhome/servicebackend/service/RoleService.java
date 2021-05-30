package pro.dengyi.myhome.servicebackend.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import pro.dengyi.myhome.myhomemodel.business.backend.Role;
import pro.dengyi.myhome.servicebackend.vo.RolePageVo;

import java.util.List;

/**
 * @author DengYi
 * @version v1.0
 */
public interface RoleService {
    IPage<Role> page(RolePageVo vo);

    void addOrUpdateRole(Role sysRole);

    void delRole(Role sysRole);

    List<Role> roleList();

}
