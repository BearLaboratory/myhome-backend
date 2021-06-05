package pro.dengyi.myhome.servicebackend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import pro.dengyi.myhome.common.exception.BusinessException;
import pro.dengyi.myhome.common.response.ResponseEnum;
import pro.dengyi.myhome.myhomemodel.business.backend.Role;
import pro.dengyi.myhome.myhomemodel.business.backend.RolePermissionMiddle;
import pro.dengyi.myhome.myhomemodel.business.backend.UserRoleMiddle;
import pro.dengyi.myhome.servicebackend.dao.RoleDao;
import pro.dengyi.myhome.servicebackend.dao.RolePermissionMiddleDao;
import pro.dengyi.myhome.servicebackend.dao.UserRoleMiddleDao;
import pro.dengyi.myhome.servicebackend.service.RoleService;
import pro.dengyi.myhome.servicebackend.vo.RolePageVo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author DengYi
 * @version v1.0
 */
@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleDao roleDao;
    @Autowired
    private RolePermissionMiddleDao rolePermissionMiddleDao;
    @Autowired
    private UserRoleMiddleDao userRoleMiddleDao;


    @Override
    public IPage<Role> page(RolePageVo vo) {
        IPage<Role> pageParam = new Page<>(vo.getPageNumber() == null ? 1 : vo.getPageNumber(), vo.getPageSize() == null ? 10 : vo.getPageSize());
        QueryWrapper<Role> qr = new QueryWrapper<>();
        if (StringUtils.isNotBlank(vo.getRoleName())) {
            qr.likeRight("role_name", vo.getRoleName());
        }
        qr.orderByDesc("create_time");

        IPage<Role> RoleIPage = roleDao.selectPage(pageParam, qr);
        List<Role> records = RoleIPage.getRecords();
        if (!CollectionUtils.isEmpty(records)) {
            for (Role record : records) {
                QueryWrapper<RolePermissionMiddle> qrr = new QueryWrapper<>();
                qrr.eq("role_id", record.getId());
                List<RolePermissionMiddle> rolePermissionMiddles = rolePermissionMiddleDao.selectList(qrr);
                if (!CollectionUtils.isEmpty(rolePermissionMiddles)) {
                    List<String> lists = new ArrayList<>();
                    for (RolePermissionMiddle rolePermissionMiddle : rolePermissionMiddles) {
                        lists.add(rolePermissionMiddle.getPermissionId());
                    }
                    record.setPermIds(lists);
                }
            }
        }
        return RoleIPage;
    }

    @Transactional
    @Override
    public void addOrUpdateRole(Role Role) {
        if (StringUtils.isNotBlank(Role.getId())) {
            //更新
            Role.setUpdateTime(new Date());
            roleDao.updateById(Role);
        } else {
            QueryWrapper<Role> qrr = new QueryWrapper<>();
            qrr.eq("role_name", Role.getRoleName());
            List<Role> Roles = roleDao.selectList(qrr);
            if (!CollectionUtils.isEmpty(Roles)) {
                throw new BusinessException(ResponseEnum.PARAM_ERROR);
            }
            //新增
            Role.setCreateTime(new Date());
            Role.setUpdateTime(new Date());
            roleDao.insert(Role);
        }
        //全量更新中间表
        QueryWrapper<RolePermissionMiddle> qr = new QueryWrapper<>();
        qr.eq("role_id", Role.getId());
        rolePermissionMiddleDao.delete(qr);
        //新增
        List<String> permIds = Role.getPermIds();
        if (!CollectionUtils.isEmpty(permIds)) {
            for (String permId : permIds) {
                RolePermissionMiddle middle = new RolePermissionMiddle();
                middle.setRoleId(Role.getId());
                middle.setPermissionId(permId);
                rolePermissionMiddleDao.insert(middle);
            }
        }
    }

    @Transactional
    @Override
    public void delRole(Role Role) {
        QueryWrapper<UserRoleMiddle> qr = new QueryWrapper<>();
        qr.eq("role_id", Role.getId());
        List<UserRoleMiddle> sysUserRoleMiddles = userRoleMiddleDao.selectList(qr);
        //如果在使用则不能删除
        if (!CollectionUtils.isEmpty(sysUserRoleMiddles)) {
            throw new BusinessException(ResponseEnum.ROLE_USED);
        }
        //未使用，则可删除
        roleDao.deleteById(Role.getId());
        //删除关联表
        QueryWrapper<RolePermissionMiddle> qrr = new QueryWrapper<>();
        qrr.eq("role_id", Role.getId());
        rolePermissionMiddleDao.delete(qrr);
    }

    @Override
    public List<Role> roleList() {
        QueryWrapper<Role> qrr = new QueryWrapper<>();
        return roleDao.selectList(qrr);
    }

}
