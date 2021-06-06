package pro.dengyi.myhome.servicedevice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import pro.dengyi.myhome.common.exception.BusinessException;
import pro.dengyi.myhome.common.response.ResponseEnum;
import pro.dengyi.myhome.myhomemodel.business.device.Device;
import pro.dengyi.myhome.myhomemodel.business.device.DeviceCategory;
import pro.dengyi.myhome.servicedevice.dao.DeviceCategoryDao;
import pro.dengyi.myhome.servicedevice.dao.DeviceDao;
import pro.dengyi.myhome.servicedevice.service.DeviceCategoryService;
import pro.dengyi.myhome.servicedevice.vo.CategoryPageVo;

import java.util.Date;
import java.util.List;

/**
 * @author DengYi
 * @version v1.0
 */
@Service
public class DeviceCategoryServiceImpl implements DeviceCategoryService {
    @Autowired
    private DeviceCategoryDao deviceCategoryDao;
    @Autowired
    private DeviceDao deviceDao;


    @Override
    public Page<DeviceCategory> page(CategoryPageVo vo) {
        Page<DeviceCategory> pageParam = new Page<>(vo.getPageNumber() == null ? 1 : vo.getPageNumber(), vo.getPageSize() == null ? 10 : vo.getPageSize());
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

    @Transactional
    @Override
    public void delCategory(DeviceCategory deviceCategory) {
        List<Device> deviceList = deviceDao.selectList(new LambdaQueryWrapper<Device>().eq(Device::getCategoryId, deviceCategory.getId()));
        if (!CollectionUtils.isEmpty(deviceList)) {
            throw new BusinessException(ResponseEnum.CATEGORY_USED);
        }
        deviceCategoryDao.deleteById(deviceCategory.getId());
    }
}
