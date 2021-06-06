package pro.dengyi.myhome.servicebackend.apis.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.stereotype.Component;
import pro.dengyi.myhome.common.exception.BusinessException;
import pro.dengyi.myhome.common.response.BaseResponse;
import pro.dengyi.myhome.common.response.DataResponse;
import pro.dengyi.myhome.common.response.ResponseEnum;
import pro.dengyi.myhome.myhomemodel.business.device.Device;
import pro.dengyi.myhome.myhomemodel.business.device.DeviceCategory;
import pro.dengyi.myhome.myhomemodel.business.vo.DevicePageVo;
import pro.dengyi.myhome.servicebackend.apis.DeviceApi;
import pro.dengyi.myhome.servicebackend.vo.CategoryPageVo;

/**
 * @author DengYi
 * @version v1.0
 */
@Component
public class DeviceApiImpl implements DeviceApi {
    @Override
    public DataResponse<Page<DeviceCategory>> conditionPage(CategoryPageVo vo) {
        throw new BusinessException(ResponseEnum.SERVICE_CALL_ERROR);
    }

    @Override
    public BaseResponse addOrUpdateCategory(DeviceCategory deviceCategory) {
        throw new BusinessException(ResponseEnum.SERVICE_CALL_ERROR);
    }

    @Override
    public BaseResponse delCategory(DeviceCategory deviceCategory) {
        throw new BusinessException(ResponseEnum.SERVICE_CALL_ERROR);
    }

    @Override
    public DataResponse<Page<Device>> page(DevicePageVo vo) {
        throw new BusinessException(ResponseEnum.SERVICE_CALL_ERROR);
    }

    @Override
    public BaseResponse publishDevice(Device device) {
        throw new BusinessException(ResponseEnum.SERVICE_CALL_ERROR);
    }

    @Override
    public BaseResponse addOrUpdateDevice(Device device) {
        throw new BusinessException(ResponseEnum.SERVICE_CALL_ERROR);
    }

}
