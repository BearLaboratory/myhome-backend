package pro.dengyi.myhome.servicebackend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import pro.dengyi.myhome.common.exception.BusinessException;
import pro.dengyi.myhome.common.response.ResponseEnum;
import pro.dengyi.myhome.myhomemodel.business.backend.Permission;
import pro.dengyi.myhome.myhomemodel.business.backend.User;
import pro.dengyi.myhome.myhomemodel.business.backend.UserRoleMiddle;
import pro.dengyi.myhome.myhomeutil.PasswordUtil;
import pro.dengyi.myhome.myhomeutil.SysUserTokenUtil;
import pro.dengyi.myhome.myhomeutil.holder.SysUserHolder;
import pro.dengyi.myhome.myhomeutil.holder.SysUserHolderModel;
import pro.dengyi.myhome.servicebackend.dao.PermissionDao;
import pro.dengyi.myhome.servicebackend.dao.UserDao;
import pro.dengyi.myhome.servicebackend.dao.UserRoleMiddleDao;
import pro.dengyi.myhome.servicebackend.dto.PermissionDto;
import pro.dengyi.myhome.servicebackend.dto.PermissionMenuDto;
import pro.dengyi.myhome.servicebackend.dto.UserDto;
import pro.dengyi.myhome.servicebackend.service.UserService;
import pro.dengyi.myhome.servicebackend.vo.LoginVo;
import pro.dengyi.myhome.servicebackend.vo.PageVo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author DengYi
 * @version v1.0
 */
@Slf4j
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao sysUserDao;
    @Autowired
    private PermissionDao permissionDao;
    @Autowired
    private UserRoleMiddleDao sysUserRoleMiddleDao;
    private static final String DEFAULT_AVATAR = "https://image.dengyi.pro/dengyi/1613733826939384.jpg";

    @Transactional
    @Override
    public String login(LoginVo loginVo) {
        QueryWrapper<User> qr = new QueryWrapper<>();
        qr.eq("phone", loginVo.getPhone());
        User user = sysUserDao.selectOne(qr);
        if (user != null) {
            if (PasswordUtil.match(loginVo.getPassword(), user.getPassword())) {
                //更新登录时间到updateTime
                user.setUpdateTime(new Date());
                sysUserDao.updateById(user);
                SysUserHolderModel sysUserHolderModel = new SysUserHolderModel();
                sysUserHolderModel.setId(user.getId());
                sysUserHolderModel.setIsDeveloper(user.getIsDeveloper());
                sysUserHolderModel.setIsSuperAdmin(user.getIsSuperAdmin());
                return SysUserTokenUtil.genToken(sysUserHolderModel);
            }
        }
        throw new BusinessException(ResponseEnum.USER_NOT_EXIST_OR_PASS_ERROR);
    }

    @Override
    public User getUserInfo() {
        String userId = SysUserHolder.getUserId();
        User sysUser = sysUserDao.selectById(userId);
        sysUser.setPassword(null);
        return sysUser;
    }


    @Transactional
    @Override
    public void addOrUpdateUser(User sysUser) {
        if (sysUser.getIsSuperAdmin() == null || !sysUser.getIsSuperAdmin()) {
            if (StringUtils.isBlank(sysUser.getId())) {
                QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
                userQueryWrapper.eq("phone", sysUser.getPhone());
                List<User> sysUsers = sysUserDao.selectList(userQueryWrapper);
                if (!CollectionUtils.isEmpty(sysUsers)) {
                    throw new BusinessException(ResponseEnum.USER_EXIST);
                }
                //新增用户
                sysUser.setPassword(PasswordUtil.encodePassword(sysUser.getPassword()));
                sysUser.setAvatar(DEFAULT_AVATAR);
                sysUser.setIsSuperAdmin(false);
                sysUser.setIsPlatformManager(true);
                sysUser.setIsDeveloper(false);
                sysUser.setCreateTime(new Date());
                sysUser.setUpdateTime(new Date());
                sysUserDao.insert(sysUser);
                //新增全选中间表
                UserRoleMiddle middle = new UserRoleMiddle();
                middle.setRoleId(sysUser.getRoleId());
                middle.setUserId(sysUser.getId());
                sysUserRoleMiddleDao.insert(middle);
            } else {
                //修改用户
                User sysUserForUpdate = sysUserDao.selectById(sysUser.getId());
                sysUserForUpdate.setName(sysUser.getName());
                sysUserForUpdate.setRoleId(sysUser.getRoleId());
                if (StringUtils.isNotBlank(sysUser.getPassword())) {
                    //密码不为空则修改密码
                    sysUserForUpdate.setPassword(PasswordUtil.encodePassword(sysUser.getPassword()));
                }
                sysUserForUpdate.setUpdateTime(new Date());
                sysUserDao.updateById(sysUserForUpdate);
                //全量更新角色中间表
                QueryWrapper<UserRoleMiddle> middleQueryWrapper = new QueryWrapper<>();
                middleQueryWrapper.eq("user_id", sysUser.getId());
                sysUserRoleMiddleDao.delete(middleQueryWrapper);

                UserRoleMiddle middle = new UserRoleMiddle();
                middle.setRoleId(sysUser.getRoleId());
                middle.setUserId(sysUser.getId());
                sysUserRoleMiddleDao.insert(middle);
            }
        }

    }


    @Override
    public PermissionDto getPermissionTree() {
        PermissionDto permissionDto = new PermissionDto();
        Boolean isSuperAdmin = SysUserHolder.getUser().getIsSuperAdmin();
        //权限分为1.菜单树权限2.按钮权限3.API权限。返回给前端只返回菜单及按钮即可，api权限只在系统内使用
        if (isSuperAdmin) {
            //超级管理员查询所有权限
            List<PermissionMenuDto> firstList = permissionDao.selectFirstMenuPermissionForSuperAdmin();
            iteAllMenu(firstList);
            permissionDto.setMenus(firstList);
            List<Permission> parentList = new ArrayList<>();
            List<Permission> firstMenuList = permissionDao.selectFirstButtonPermissionForSuperAdmin();
            iteAllButton(firstMenuList, parentList);
            permissionDto.setButtons(parentList);
        } else {
            //查询用户角色对应的权限
            //1, 菜单权限要构造成树
            //1.1, 查询一级菜单
            List<PermissionMenuDto> firstList = permissionDao.selectFirstMenuPermissionByUserId(SysUserHolder.getUserId());
            //1.2, 迭代查询所有子菜单权限
            iteAllMenu(firstList);
            permissionDto.setMenus(firstList);

            //2, 按钮权限只要构造成集合即可
            List<Permission> parentList = new ArrayList<>();
            List<Permission> firstMenuList = permissionDao.selectFirstButtonPermissionByUserId(SysUserHolder.getUserId());
            iteAllButton(firstMenuList, parentList);
            permissionDto.setButtons(parentList);
        }


        return permissionDto;
    }

    @Override
    public List<String> getAllPermissionsList() {
        List<String> stringList = new ArrayList<>();
        Boolean isSuperAdmin = SysUserHolder.getUser().getIsSuperAdmin();
        if (isSuperAdmin) {
            List<PermissionMenuDto> firstList = permissionDao.selectFirstMenuPermissionForSuperAdmin();
            iteAllMenuString(firstList, stringList);
            List<Permission> firstMenuList = permissionDao.selectFirstButtonPermissionForSuperAdmin();
            iteAllButtonString(firstMenuList, stringList);
        } else {
            //todo 查询普通用户

        }
        return stringList;
    }

    @Override
    public IPage<UserDto> page(PageVo pageVo) {
        IPage<UserDto> iPage = new Page<>(pageVo.getPageNumber() == null ? 1 : pageVo.getPageNumber(), pageVo.getPageSize() == null ? 10 : pageVo.getPageSize());
        return sysUserDao.customPage(iPage, pageVo);
    }

    @Transactional
    @Override
    public void delUser(User user) {
        User userSaved = sysUserDao.selectById(user.getId());
        if (userSaved.getIsSuperAdmin()) {
            // 超级管理员不允许删除
            throw new BusinessException(ResponseEnum.PARAM_ERROR);
        }
        //普通用户直接删除
        sysUserDao.deleteById(userSaved.getId());
    }

    /**
     * 迭代所有子菜单方法
     *
     * @param firstList
     */
    private void iteAllMenu(List<PermissionMenuDto> firstList) {
        if (!CollectionUtils.isEmpty(firstList)) {
            for (PermissionMenuDto permissionMenuDto : firstList) {
                //根据父ID查询所有子权限
                List<PermissionMenuDto> childList = permissionDao.selectChildMenuPermissionByParentId(permissionMenuDto.getId());
                if (!CollectionUtils.isEmpty(childList)) {
                    permissionMenuDto.setChildren(childList);
                    iteAllMenu(childList);
                }
            }
        }
    }

    private void iteAllMenuString(List<PermissionMenuDto> firstList, List<String> stringList) {
        if (!CollectionUtils.isEmpty(firstList)) {
            for (PermissionMenuDto permissionMenuDto : firstList) {
                stringList.add(permissionMenuDto.getPermissionName());
                //根据父ID查询所有子权限
                List<PermissionMenuDto> childList = permissionDao.selectChildMenuPermissionByParentId(permissionMenuDto.getId());
                if (!CollectionUtils.isEmpty(childList)) {
                    iteAllMenuString(childList, stringList);
                }
            }
        }
    }

    /**
     * 迭代所有子按钮方法
     *
     * @param firstMenuList
     * @param parentList
     */
    private void iteAllButton(List<Permission> firstMenuList, List<Permission> parentList) {
        if (!CollectionUtils.isEmpty(firstMenuList)) {
            parentList.addAll(firstMenuList);
            for (Permission sysPermission : firstMenuList) {
                //根据父ID查询所有子权限
                List<Permission> childList = permissionDao.selectChildButtonPermissionByParentId(sysPermission.getId());
                iteAllButton(childList, parentList);
            }
        }
    }

    private void iteAllButtonString(List<Permission> firstMenuList, List<String> stringList) {
        if (!CollectionUtils.isEmpty(firstMenuList)) {
            for (Permission sysPermission : firstMenuList) {
                stringList.add(sysPermission.getPermissionName());
                //根据父ID查询所有子权限
                List<Permission> childList = permissionDao.selectChildButtonPermissionByParentId(sysPermission.getId());
                iteAllButtonString(childList, stringList);
            }
        }
    }

}
