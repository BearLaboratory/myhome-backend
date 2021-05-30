package pro.dengyi.myhome.servicebackend.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pro.dengyi.myhome.common.response.BaseResponse;
import pro.dengyi.myhome.common.response.DataResponse;
import pro.dengyi.myhome.common.response.ResponseEnum;
import pro.dengyi.myhome.myhomemodel.business.backend.Role;
import pro.dengyi.myhome.servicebackend.service.RoleService;
import pro.dengyi.myhome.servicebackend.vo.RolePageVo;

import java.util.List;

/**
 * @author DengYi
 * @version v1.0
 */
@RestController
@RequestMapping("/sysRole")
@Api(tags = "角色接口")
public class RoleController {
    @Autowired
    private RoleService roleService;


    @ApiOperation("分页查询")
    @PostMapping("/page")
    public DataResponse<IPage<Role>> page(@RequestBody RolePageVo vo) {
        IPage<Role> iPage = roleService.page(vo);
        return new DataResponse<>(ResponseEnum.SUCCESS, iPage);
    }

    @ApiOperation("新增角色，同时分配权限")
    @PostMapping("/addOrUpdateRole")
    public BaseResponse addOrUpdateRole(@RequestBody Role sysRole) {
        roleService.addOrUpdateRole(sysRole);
        return new BaseResponse(ResponseEnum.SUCCESS);
    }

    @ApiOperation("删除角色")
    @PostMapping("/delRole")
    public BaseResponse delRole(@RequestBody Role sysRole) {
        roleService.delRole(sysRole);
        return new BaseResponse(ResponseEnum.SUCCESS);
    }

    @ApiOperation("角色列表")
    @GetMapping("/roleList")
    public DataResponse<List<Role>> roleList() {
        List<Role> list = roleService.roleList();
        return new DataResponse<>(ResponseEnum.SUCCESS, list);
    }


}
