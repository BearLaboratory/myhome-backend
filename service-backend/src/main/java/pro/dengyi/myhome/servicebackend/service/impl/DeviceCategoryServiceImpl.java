package pro.dengyi.myhome.servicebackend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pro.dengyi.myhome.myhomemodel.business.device.DeviceCategory;
import pro.dengyi.myhome.servicebackend.dao.DeviceCategoryDao;
import pro.dengyi.myhome.servicebackend.service.DeviceCategoryService;
import pro.dengyi.myhome.servicebackend.vo.CategoryPageVo;

import java.util.Date;

/**
 * @author DengYi
 * @version v1.0
 */
@Service
public class DeviceCategoryServiceImpl implements DeviceCategoryService {
    @Autowired
    private DeviceCategoryDao deviceCategoryDao;

    @Override
    public IPage<DeviceCategory> page(CategoryPageVo vo) {
        IPage<DeviceCategory> pageParam = new Page<>(vo.getPageNumber() == null ? 1 : vo.getPageNumber(), vo.getPageSize() == null ? 10 : vo.getPageSize());
        QueryWrapper<DeviceCategory> qr = new QueryWrapper<>();
        if (StringUtils.isNotBlank(vo.getName())) {
            qr.like("name", vo.getName());
        }
        return deviceCategoryDao.selectPage(pageParam, qr);
    }

    @Transactional
    @Override
    public void addOrUpdate(DeviceCategory deviceCategory) {
        if (StringUtils.isBlank(deviceCategory.getId())) {
            //新增
            deviceCategory.setCreateTime(new Date());
            deviceCategory.setUpdateTime(new Date());
            deviceCategoryDao.insert(deviceCategory);
        } else {
            deviceCategory.setUpdateTime(new Date());
            deviceCategoryDao.updateById(deviceCategory);
        }
    }
}
