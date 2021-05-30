package pro.dengyi.myhome.servicebackend.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import pro.dengyi.myhome.myhomemodel.business.backend.User;
import pro.dengyi.myhome.servicebackend.dto.PermissionDto;
import pro.dengyi.myhome.servicebackend.dto.UserDto;
import pro.dengyi.myhome.servicebackend.vo.LoginVo;
import pro.dengyi.myhome.servicebackend.vo.PageVo;

import java.util.List;

/**
 * @author DengYi
 * @version v1.0
 */
public interface UserService {

    /**
     * 根据手机号和密码登录，登录后返回token
     *
     * @param loginVo
     * @return
     */
    String login(LoginVo loginVo);

    /**
     * 获取登录用户的信息
     *
     * @return
     */
    User getUserInfo();


    /**
     * 新增或修改用户，判断的依据是有无ID
     * 方法中会检查所有新增的用户不能为超级管理员
     *
     * @param sysUser
     */
    void addOrUpdateUser(User sysUser);


    /**
     * 根据用户ID查询用户菜单按钮权限集合
     *
     * @return
     */
    PermissionDto getPermissionTree();

    List<String> getAllPermissionsList();

    IPage<UserDto> page(PageVo pageVo);

}
