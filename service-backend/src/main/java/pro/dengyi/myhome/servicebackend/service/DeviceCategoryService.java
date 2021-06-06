package pro.dengyi.myhome.servicebackend.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import pro.dengyi.myhome.myhomemodel.business.device.DeviceCategory;
import pro.dengyi.myhome.servicebackend.vo.CategoryPageVo;

/**
 * @author DengYi
 * @version v1.0
 */
public interface DeviceCategoryService {
    IPage<DeviceCategory> page(CategoryPageVo vo);

    /**
     * 新增或修改设备分类
     *
     * @param deviceCategory 设备分类实体
     */
    void addOrUpdate(DeviceCategory deviceCategory);
}
