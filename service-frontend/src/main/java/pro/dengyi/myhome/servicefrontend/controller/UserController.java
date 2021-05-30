package pro.dengyi.myhome.servicefrontend.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pro.dengyi.myhome.common.response.DataResponse;
import pro.dengyi.myhome.common.response.ResponseEnum;
import pro.dengyi.myhome.servicefrontend.dto.UserInfoDto;
import pro.dengyi.myhome.servicefrontend.service.UserService;
import pro.dengyi.myhome.servicefrontend.vo.PhoneLoginVo;

import javax.servlet.http.HttpServletRequest;

/**
 * @author BLab
 */
@Api(tags = "用户接口")
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @ApiOperation("手机号验证码登录——系统唯一支持登录方式是手机号验证码登录")
    @PostMapping("/loginByPhone")
    public DataResponse<String> loginByPhone(@RequestBody @Validated PhoneLoginVo vo, HttpServletRequest request) {
        String token = userService.loginByPhone(vo, request);
        return new DataResponse<>(ResponseEnum.SUCCESS, token);
    }

    @ApiOperation("查询用户信息")
    @GetMapping("/getUserInfo")
    public DataResponse<UserInfoDto> getUserInfo() {
        UserInfoDto userInfoDto = userService.getUserInfo();
        return new DataResponse<>(ResponseEnum.SUCCESS, userInfoDto);
    }


//    @ApiOperation("根据用户手机号查询用户是否存在")
//    @GetMapping("/searchUserByPhone")
//    public DataResponse<Boolean> searchUserByPhone(String phone) {
//        if (StringUtils.isBlank(phone) || !PhoneUtil.isLegalPhone(phone)) {
//            throw new BusinessException(ResponseEnum.PARAM_ERROR);
//        }
//        Boolean searchStatus = userService.searchUserByPhone(phone);
//        return new DataResponse<>(ResponseEnum.SUCCESS, searchStatus);
//    }
//
//    @ApiOperation("添加用户到家庭，只是发送消息需要用户同意")
//    @GetMapping("/addUser2Family")
//    public BaseResponse addUser2Family(HashMap<String, String> addParam) {
//        if (addParam == null) {
//            throw new BusinessException(ResponseEnum.PARAM_ERROR);
//        }
//        userService.addUser2Family(addParam);
//        return new BaseResponse(ResponseEnum.SUCCESS);
//    }
//
//    @ApiOperation("手机号密码登录")
//    @PostMapping("/loginByPhonePassword")
//    public DataResponse<String> loginByPhonePassword(@RequestBody @Validated LoginByPhonePasswordVo vo) {
//        String token = userService.loginByPhonePassword(vo);
//        return new DataResponse<>(ResponseEnum.SUCCESS, token);
//    }
//
//    @ApiOperation("手机号密码注册")
//    @PostMapping("/registByPhonePassword")
//    public DataResponse<String> registByPhonePassword(@RequestBody @Validated RegistByPhonePasswordVo vo) {
//        String token = userService.registByPhonePassword(vo);
//        return new DataResponse<>(ResponseEnum.SUCCESS, token);
//    }
//
//

//
////    @ApiOperation("更新用户信息")
////    @PostMapping("/updateUserInfo")
////    public BaseResponse updateUserInfo(@RequestBody @Validated User user) {
////        userService.updateUserInfo(user);
////        return new BaseResponse(ResponseEnum.SUCCESS);
////    }
//
////    @ApiOperation("上报用户选择的家庭或房间")
////    @PostMapping("/updateUserSelected")
////    public BaseResponse updateUserSelected(@RequestBody @Validated User user) {
////        userService.updateUserSelected(user);
////        return new BaseResponse(ResponseEnum.SUCCESS);
////    }
//
//    @ApiOperation("用户APP在线状态上传")
//    @PostMapping("/userAppOnline")
//    public BaseResponse userAppOnline(@RequestBody @Validated AppOnlineVo vo) {
//        userService.userAppOnline(vo);
//        return new BaseResponse(ResponseEnum.SUCCESS);
//    }
//
//    @ApiOperation("查询是否有未读消息")
//    @GetMapping("/getHaveNotRead")
//    public DataResponse<Boolean> getHaveNotRead() {
//        Boolean haveNotRead = userService.getHaveNotRead();
//        return new DataResponse<>(ResponseEnum.SUCCESS, haveNotRead);
//    }


}
