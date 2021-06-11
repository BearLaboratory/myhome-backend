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
import pro.dengyi.myhome.myhomemodel.business.frontend.Family;
import pro.dengyi.myhome.servicefrontend.service.FamilyService;

import java.util.List;

/**
 * @author BLab
 */
@Api(tags = "家庭接口")
@RestController
@RequestMapping("/family")
public class FamilyController {
    @Autowired
    private FamilyService familyService;

    @ApiOperation("新增或修改家庭")
    @PostMapping("/addOrUpdate")
    public BaseResponse addOrUpdate(@RequestBody @Validated Family family) {
        familyService.addOrUpdate(family);
        return new BaseResponse(ResponseEnum.SUCCESS);
    }

    @ApiOperation("查询用户所有家庭列表")
    @GetMapping("/familyList")
    public DataResponse<List<Family>> familyList() {
        List<Family> familyList = familyService.familyList();
        return new DataResponse<>(ResponseEnum.SUCCESS, familyList);
    }


    @ApiOperation("查询用户是否是家庭户主")
    @GetMapping("/checkIsHolder")
    public DataResponse<Boolean> checkIsHolder(@RequestParam @ApiParam(value = "家庭ID", required = true) String familyId) {
        if (StringUtils.isBlank(familyId)) {
            throw new BusinessException(ResponseEnum.PARAM_ERROR);
        }
        Boolean isHolder = familyService.checkIsHolder(familyId);
        return new DataResponse<>(ResponseEnum.SUCCESS, isHolder);
    }

    @ApiOperation("同意加入家庭")
    @GetMapping("/confirmJoin")
    public BaseResponse confirmJoin(String messageId) {
        familyService.confirmJoin(messageId);
        return new BaseResponse(ResponseEnum.SUCCESS);
    }

}
