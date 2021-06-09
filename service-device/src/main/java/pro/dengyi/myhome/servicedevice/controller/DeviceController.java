package pro.dengyi.myhome.servicedevice.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pro.dengyi.myhome.common.response.BaseResponse;
import pro.dengyi.myhome.common.response.DataResponse;
import pro.dengyi.myhome.common.response.ResponseEnum;
import pro.dengyi.myhome.myhomemodel.business.device.Device;
import pro.dengyi.myhome.myhomemodel.business.vo.DevicePageVo;
import pro.dengyi.myhome.servicedevice.service.DeviceService;

/**
 * 设备controller
 *
 * @author DengYi
 * @version v1.0
 */
@RestController
@RequestMapping("/device")
@Api(tags = "设备接口")
public class DeviceController {
    @Autowired
    private DeviceService deviceService;


    @ApiOperation("条件分页查询")
    @PostMapping("/page")
    public DataResponse<Page<Device>> page(@RequestBody DevicePageVo vo) {
        Page<Device> page = deviceService.page(vo);
        return new DataResponse<>(ResponseEnum.SUCCESS, page);
    }

    @ApiOperation("发布设备")
    @PostMapping("/publishDevice")
    BaseResponse publishDevice(@RequestBody Device device) {
        deviceService.publishDevice(device);
        return new BaseResponse(ResponseEnum.SUCCESS);
    }

    @ApiOperation("新增设备")
    @PostMapping("/addOrUpdateDevice")
    BaseResponse addOrUpdateDevice(@RequestBody Device device) {
        deviceService.addOrUpdateDevice(device);
        return new BaseResponse(ResponseEnum.SUCCESS);
    }

    @ApiOperation("控制设备设备")
    @PostMapping("/controlDevice")
    BaseResponse controlDevice(@RequestBody Device device) {
        deviceService.controlDevice(device);
        return new BaseResponse(ResponseEnum.SUCCESS);
    }
}
