package pro.dengyi.myhome.servicefrontend.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pro.dengyi.myhome.common.response.BaseResponse;
import pro.dengyi.myhome.common.response.DataResponse;
import pro.dengyi.myhome.common.response.ResponseEnum;
import pro.dengyi.myhome.myhomemodel.business.Device;
import pro.dengyi.myhome.servicefrontend.service.DeviceService;

import java.util.List;

/**
 * @author BLab
 */
@Api(tags = "设备")
@RestController
@RequestMapping("/device")
public class DeviceController {
    @Autowired
    private DeviceService deviceService;

    @ApiOperation("根据房间ID查询所属设备")
    @GetMapping("/listByRoomId")
    public DataResponse<List<Device>> listByRoomId(String familyId, String roomId, Boolean favorite) {
        List<Device> deviceList = deviceService.listByRoomId(familyId, roomId, favorite);
        return new DataResponse<>(ResponseEnum.SUCCESS, deviceList);
    }

    @ApiOperation("改变设备的收藏状态")
    @PostMapping("/changeFavorite")
    public BaseResponse changeFavorite(@RequestBody @Validated Device device) {
        deviceService.changeFavorite(device);
        return new BaseResponse(ResponseEnum.SUCCESS);
    }

    @ApiOperation("控制设备")
    @PostMapping("/doControl")
    public BaseResponse doControl(@RequestBody @Validated Device device) {
        deviceService.doControl(device);
        return new BaseResponse(ResponseEnum.SUCCESS);
    }

    @ApiOperation("添加设备")
    @PostMapping("/addDevice")
    public BaseResponse addDevice(@RequestBody @Validated Device device) {
        deviceService.addDevice(device);
        return new BaseResponse(ResponseEnum.SUCCESS);
    }


}
