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
import pro.dengyi.myhome.myhomemodel.business.frontend.Floor;
import pro.dengyi.myhome.servicefrontend.service.FloorService;

import java.util.List;

/**
 * @author BLab
 */
@Api(tags = "楼层接口")
@RestController
@RequestMapping("/floor")
public class FloorController {
    @Autowired
    private FloorService floorService;

    @ApiOperation("新增或修改楼层信息")
    @PostMapping("/addOrUpdate")
    public BaseResponse addOrUpdate(@RequestBody @Validated Floor floor) {
        floorService.addOrUpdate(floor);
        return new BaseResponse(ResponseEnum.SUCCESS);
    }

    @ApiOperation("查询家庭所有楼层列表")
    @GetMapping("/listByFamilyId")
    public DataResponse<List<Floor>> listByFamilyId(@RequestParam @ApiParam(value = "家庭ID", required = true) String familyId) {
        if (StringUtils.isBlank(familyId)) {
            throw new BusinessException(ResponseEnum.PARAM_ERROR);
        }
        List<Floor> lists = floorService.listByFamilyId(familyId);
        return new DataResponse<>(ResponseEnum.SUCCESS, lists);
    }

    @ApiOperation("删除楼层")
    @PostMapping("/deleteFloor")
    public BaseResponse deleteFloor(@RequestBody Floor floor) {
        floorService.deleteFloor(floor);
        return new BaseResponse(ResponseEnum.SUCCESS);
    }


}
