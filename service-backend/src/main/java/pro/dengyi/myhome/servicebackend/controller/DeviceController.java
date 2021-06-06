package pro.dengyi.myhome.servicebackend.controller;

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
import pro.dengyi.myhome.myhomemodel.business.device.Device;
import pro.dengyi.myhome.myhomemodel.business.vo.DevicePageVo;
import pro.dengyi.myhome.servicebackend.apis.DeviceApi;
import pro.dengyi.myhome.servicebackend.service.DeviceService;

/**
 * 设备类型管理controller
 *
 * @author DengYi
 * @version v1.0
 */
@RestController
@RequestMapping("/device")
@Api(tags = "设备接口")
public class DeviceController {
    @Autowired
    private DeviceApi deviceApi;
    @Autowired
    private DeviceService deviceService;



    @ApiOperation("条件分页查询")
    @PostMapping("/page")
    public DataResponse<Page<Device>> page(@RequestBody DevicePageVo vo) {
        return deviceApi.page(vo);
    }

    @ApiOperation("发布设备，发布后即可给用户使用")
    @PostMapping("/publishDevice")
    public BaseResponse publishDevice(@RequestBody Device device) {
        return deviceApi.publishDevice(device);
    }

    @ApiOperation("新增设备")
    @PostMapping("/addOrUpdate")
    public BaseResponse addOrUpdate(@RequestBody Device device) {
        return deviceService.addOrUpdate(device);
    }

}
