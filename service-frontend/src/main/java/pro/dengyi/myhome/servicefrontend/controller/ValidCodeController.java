package pro.dengyi.myhome.servicefrontend.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pro.dengyi.myhome.common.exception.BusinessException;
import pro.dengyi.myhome.common.response.BaseResponse;
import pro.dengyi.myhome.common.response.ResponseEnum;
import pro.dengyi.myhome.myhomeutil.PhoneUtil;
import pro.dengyi.myhome.servicefrontend.service.ValidCodeService;

/**
 * 验证码controller
 *
 * @author DengYi
 * @version v1.0
 */
@Api(tags = "验证码接口")
@RestController
@RequestMapping("/validCode")
public class ValidCodeController {
    @Autowired
    private ValidCodeService validCodeService;

    @ApiOperation("通过手机号获取验证码")
    @GetMapping("/getCodeByPhone")
    public BaseResponse getCodeByPhone(String phoneString) {
        if (StringUtils.isBlank(phoneString) || !PhoneUtil.isLegalPhone(phoneString)) {
            throw new BusinessException(ResponseEnum.PARAM_ERROR);
        }
        validCodeService.getCodeByPhone(phoneString);
        return new BaseResponse(ResponseEnum.SUCCESS);
    }


}
