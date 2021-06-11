package pro.dengyi.myhome.servicedevice.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import pro.dengyi.myhome.myhomemodel.business.device.DeviceCategory;
import pro.dengyi.myhome.servicedevice.vo.CategoryPageVo;


/**
 * @author DengYi
 * @version v1.0
 */
public interface DeviceCategoryService {
    Page<DeviceCategory> page(CategoryPageVo vo);

    /**
     * 新增或修改设备分类
     *
     * @param deviceCategory 设备分类实体
     */
    void addOrUpdate(DeviceCategory deviceCategory);

    void delCategory(DeviceCategory deviceCategory);
}
