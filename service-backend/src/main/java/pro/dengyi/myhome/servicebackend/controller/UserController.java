package pro.dengyi.myhome.servicebackend.controller;


import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pro.dengyi.myhome.common.response.BaseResponse;
import pro.dengyi.myhome.common.response.DataResponse;
import pro.dengyi.myhome.common.response.ResponseEnum;
import pro.dengyi.myhome.myhomemodel.business.backend.User;
import pro.dengyi.myhome.servicebackend.dto.PermissionDto;
import pro.dengyi.myhome.servicebackend.dto.UserDto;
import pro.dengyi.myhome.servicebackend.service.UserService;
import pro.dengyi.myhome.servicebackend.vo.LoginVo;
import pro.dengyi.myhome.servicebackend.vo.PageVo;

import java.util.List;

/**
 * 系统管理员接口
 * <p>
 * 1.注册功能只提供给开发者注册，因此接口中默认给注册进来的用户开发者权限
 * 2. 后台管理员只通过有权限的人员进行分配
 *
 * @author DengYi
 * @version v1.0
 */

@RestController
@RequestMapping("/sysUser")
@Api(tags = "系统管理员接口")
public class UserController {
    @Autowired
    private UserService userService;

    @ApiOperation("用户名密码登录")
    @PostMapping("/login")
    public DataResponse<String> login(@RequestBody @Validated LoginVo loginVo) {
        String token = userService.login(loginVo);
        return new DataResponse<>(ResponseEnum.SUCCESS, token);
    }


    @ApiOperation("获取用户信息")
    @GetMapping("/getUserInfo")
    public DataResponse<User> getUserInfo() {
        User user = userService.getUserInfo();
        return new DataResponse<>(ResponseEnum.SUCCESS, user);
    }

    @ApiOperation("查询用户的所有权限树集合和按钮集合")
    @GetMapping("/getPermissionTree")
    public DataResponse<PermissionDto> getPermissionTree() {
        PermissionDto permissionDto = userService.getPermissionTree();
        return new DataResponse<>(ResponseEnum.SUCCESS, permissionDto);
    }


    @ApiOperation("网关——查询用户的所有权限集合一位数组")
    @GetMapping("/getAllPermissionsList")
    public List<String> getAllPermissionsList() {
        return userService.getAllPermissionsList();
    }


    @SentinelResource(value = "user", blockHandler = "demoHandler")
    @ApiOperation("条件分页查询用户")
    @GetMapping("/page")
    public DataResponse<IPage<UserDto>> page(PageVo pageVo) {
        IPage<UserDto> pageResponse = userService.page(pageVo);
        return new DataResponse<>(ResponseEnum.SUCCESS, pageResponse);
    }

    @ApiOperation("新增或修改管理后台用户,修改用户时密码为空则不修改密码")
    @PostMapping("/addOrUpdateUser")
    public BaseResponse addOrUpdateUser(@RequestBody @Validated User user) {
        userService.addOrUpdateUser(user);
        return new BaseResponse(ResponseEnum.SUCCESS);
    }

    @ApiOperation("删除用户")
    @PostMapping("/delUser")
    public BaseResponse delUser(@RequestBody @Validated User user) {
        userService.delUser(user);
        return new BaseResponse(ResponseEnum.SUCCESS);
    }


}
