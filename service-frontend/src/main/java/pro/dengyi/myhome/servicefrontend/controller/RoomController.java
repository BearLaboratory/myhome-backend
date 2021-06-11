package pro.dengyi.myhome.servicefrontend.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pro.dengyi.myhome.common.exception.BusinessException;
import pro.dengyi.myhome.common.response.BaseResponse;
import pro.dengyi.myhome.common.response.DataResponse;
import pro.dengyi.myhome.common.response.ResponseEnum;
import pro.dengyi.myhome.myhomemodel.business.frontend.Room;
import pro.dengyi.myhome.servicefrontend.service.RoomService;

import java.util.List;

/**
 * @author BLab
 */
@Api(tags = "房间接口")
@RestController
@RequestMapping("/room")
public class RoomController {
    @Autowired
    private RoomService roomService;


    @ApiOperation("新增或修改楼层信息")
    @PostMapping("/addOrUpdate")
    public BaseResponse addOrUpdate(@RequestBody @Validated Room room) {
        roomService.addOrUpdate(room);
        return new BaseResponse(ResponseEnum.SUCCESS);
    }

    @ApiOperation("查询楼层所有房间列表")
    @GetMapping("/listByFloorId")
    public DataResponse<List<Room>> list(@RequestParam @ApiParam(value = "楼层ID", required = true) String floorId) {
        if (StringUtils.isBlank(floorId)) {
            throw new BusinessException(ResponseEnum.PARAM_ERROR);
        }
        List<Room> lists = roomService.list(floorId);
        return new DataResponse<>(ResponseEnum.SUCCESS, lists);
    }

    @ApiOperation("删除房间")
    @PostMapping("/deleteRoom")
    public BaseResponse deleteRoom(@RequestBody Room room) {
        roomService.deleteRoom(room);
        return new BaseResponse(ResponseEnum.SUCCESS);
    }

    @ApiOperation("查询家庭的所有房间列表")
    @GetMapping("/listAllByFamilyId")
    public DataResponse<List<Room>> listAllByFamilyId(@RequestParam @ApiParam(value = "家庭ID", required = true) String familyId) {
        if (StringUtils.isBlank(familyId)) {
            throw new BusinessException(ResponseEnum.PARAM_ERROR);
        }
        List<Room> lists = roomService.listAllByFamilyId(familyId);
        return new DataResponse<>(ResponseEnum.SUCCESS, lists);
    }


}
