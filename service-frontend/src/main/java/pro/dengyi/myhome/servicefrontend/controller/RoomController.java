package pro.dengyi.myhome.servicefrontend.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pro.dengyi.myhome.common.response.BaseResponse;
import pro.dengyi.myhome.common.response.DataResponse;
import pro.dengyi.myhome.common.response.ResponseEnum;
import pro.dengyi.myhome.myhomemodel.business.Room;
import pro.dengyi.myhome.servicefrontend.service.RoomService;


import java.util.List;

/**
 * @author BLab
 */
@Api(tags = "房间")
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

    @ApiOperation("查询楼层列表")
    @GetMapping("/listByFloorId")
    public DataResponse<List<Room>> list(String floorId) {
        List<Room> lists = roomService.list(floorId);
        return new DataResponse<>(ResponseEnum.SUCCESS, lists);
    }

    @ApiOperation("查询家庭的所有房间列表")
    @GetMapping("/listAllByFamilyId")
    public DataResponse<List<Room>> listAllByFamilyId(String familyId) {
        List<Room> lists = roomService.listAllByFamilyId(familyId);
        return new DataResponse<>(ResponseEnum.SUCCESS, lists);
    }


}
