package pro.dengyi.myhome.servicebackend.apis;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Primary;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import pro.dengyi.myhome.common.response.BaseResponse;
import pro.dengyi.myhome.common.response.DataResponse;
import pro.dengyi.myhome.myhomemodel.business.device.Device;
import pro.dengyi.myhome.myhomemodel.business.device.DeviceCategory;
import pro.dengyi.myhome.myhomemodel.business.vo.DevicePageVo;
import pro.dengyi.myhome.servicebackend.vo.CategoryPageVo;

/**
 * 设备微服务api
 *
 * @author DengYi
 * @version v1.0
 */
@Primary
@FeignClient(value = "SERVICE-DEVICE")
public interface DeviceApi {

    /**
     * 设备分类条件分页查询
     *
     * @param vo
     * @return
     */
    @PostMapping("/deviceCategory/page")
    DataResponse<Page<DeviceCategory>> conditionPage(@RequestBody CategoryPageVo vo);

    /**
     * 新增或修改分类
     *
     * @param deviceCategory
     * @return
     */
    @PostMapping("/deviceCategory/addOrUpdate")
    BaseResponse addOrUpdateCategory(@RequestBody DeviceCategory deviceCategory);

    /**
     * 删除分类
     *
     * @param deviceCategory
     * @return
     */
    @PostMapping("/deviceCategory/delCategory")
    BaseResponse delCategory(@RequestBody DeviceCategory deviceCategory);

    /**
     * 设备条件分页查询
     *
     * @param vo
     * @return
     */
    @PostMapping("/device/page")
    DataResponse<Page<Device>> page(@RequestBody DevicePageVo vo);

    @PostMapping("/device/publishDevice")
    BaseResponse publishDevice(@RequestBody Device device);


    @PostMapping("/device/addOrUpdateDevice")
    BaseResponse addOrUpdateDevice(@RequestBody Device device);
}
