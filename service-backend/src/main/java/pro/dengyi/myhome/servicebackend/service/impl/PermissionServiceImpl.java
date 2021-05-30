package pro.dengyi.myhome.servicebackend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import pro.dengyi.myhome.myhomemodel.business.backend.Permission;
import pro.dengyi.myhome.servicebackend.dao.PermissionDao;
import pro.dengyi.myhome.servicebackend.dto.PermissionMenuDto;
import pro.dengyi.myhome.servicebackend.dto.PermissionPageDto;
import pro.dengyi.myhome.servicebackend.service.PermissionService;
import pro.dengyi.myhome.servicebackend.vo.PermPageVo;

import java.util.Date;
import java.util.List;

/**
 * @author DengYi
 * @version v1.0
 */
@Service
public class PermissionServiceImpl implements PermissionService {
    @Autowired
    private PermissionDao permissionDao;

    @Override
    public IPage<PermissionPageDto> page(PermPageVo vo) {
        IPage<PermissionPageDto> pageParam = new Page<>(vo.getPageNumber() == null ? 1 : vo.getPageNumber(), vo.getPageSize() == null ? 10 : vo.getPageSize());
        IPage<PermissionPageDto> iPageData = permissionDao.selectCustomPage(pageParam);
        List<PermissionPageDto> records = iPageData.getRecords();
        //查询是否有子级
        if (!CollectionUtils.isEmpty(records)) {
            for (PermissionPageDto record : records) {
                QueryWrapper<Permission> qr = new QueryWrapper<>();
                qr.eq("parent_id", record.getId());
                Integer integer = permissionDao.selectCount(qr);
                record.setHasChildren(integer > 0);
            }

        }
        return iPageData;
    }

    @Transactional
    @Override
    public void addOrUpdate(Permission sysPermission) {
        if (StringUtils.isNotBlank(sysPermission.getId())) {
            //更新
            sysPermission.setUpdateTime(new Date());
            permissionDao.updateById(sysPermission);
        } else {
            //新增
            sysPermission.setCreateTime(new Date());
            sysPermission.setUpdateTime(new Date());
            permissionDao.insert(sysPermission);
        }
    }

    @Override
    public List<PermissionPageDto> getByParentId(String parentId) {
        List<PermissionPageDto> list = permissionDao.selectByParentId(parentId);
        if (!CollectionUtils.isEmpty(list)) {
            for (PermissionPageDto permissionPageDto : list) {
                if (permissionPageDto.getType() != 1) {
                    permissionPageDto.setHasChildren(false);
                } else {
                    //查询是否有子
                    QueryWrapper<Permission> qr = new QueryWrapper<>();
                    qr.eq("parent_id", permissionPageDto.getId());
                    Integer integer = permissionDao.selectCount(qr);
                    permissionPageDto.setHasChildren(integer > 0);
                }
            }
        }
        return list;
    }

    @Override
    public List<PermissionMenuDto> totalPermissionTree() {
        //一级
        List<PermissionMenuDto> firstList = permissionDao.selectAllSubByParentId(null);
        if (!CollectionUtils.isEmpty(firstList)) {
            for (PermissionMenuDto permissionMenuDto : firstList) {
                //二级
                List<PermissionMenuDto> secondList = permissionDao.selectAllSubByParentId(permissionMenuDto.getId());
                permissionMenuDto.setChildren(secondList);
                //递归
                iterSub(secondList);
            }
        }
        return firstList;
    }

    private void iterSub(List<PermissionMenuDto> parentList) {
        if (!CollectionUtils.isEmpty(parentList)) {
            for (PermissionMenuDto menuDto : parentList) {
                List<PermissionMenuDto> thirdList = permissionDao.selectAllSubByParentId(menuDto.getId());
                menuDto.setChildren(thirdList);
                iterSub(thirdList);
            }
        }
    }
}