package pro.dengyi.myhome.servicebackend.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pro.dengyi.myhome.common.response.BaseResponse;
import pro.dengyi.myhome.common.response.DataResponse;
import pro.dengyi.myhome.common.response.ResponseEnum;
import pro.dengyi.myhome.myhomemodel.business.backend.Permission;
import pro.dengyi.myhome.servicebackend.dto.PermissionMenuDto;
import pro.dengyi.myhome.servicebackend.dto.PermissionPageDto;
import pro.dengyi.myhome.servicebackend.service.PermissionService;
import pro.dengyi.myhome.servicebackend.vo.PermPageVo;

import java.util.List;

/**
 * @author DengYi
 * @version v1.0
 */
@RestController
@RequestMapping("/sysPermission")
@Api(tags = "权限接口")
public class PermissionController {
    @Autowired
    private PermissionService permissionService;

    @ApiOperation("分页查询")
    @PostMapping("/page")
    public DataResponse<IPage<PermissionPageDto>> page(@RequestBody PermPageVo vo) {
        IPage<PermissionPageDto> iPage = permissionService.page(vo);
        return new DataResponse<>(ResponseEnum.SUCCESS, iPage);
    }

    @ApiOperation("新增或修改权限")
    @PostMapping("/addOrUpdate")
    public BaseResponse addOrUpdate(@RequestBody Permission permission) {
        permissionService.addOrUpdate(permission);
        return new BaseResponse(ResponseEnum.SUCCESS);
    }


    @ApiOperation("根据父Id查询所有子级权限")
    @GetMapping("/getByParentId")
    public DataResponse<List<PermissionPageDto>> getByParentId(String parentId) {
        List<PermissionPageDto> list = permissionService.getByParentId(parentId);
        return new DataResponse<>(ResponseEnum.SUCCESS, list);
    }

    @ApiOperation("获取完整的权限树，包含按钮树")
    @GetMapping("/totalPermissionTree")
    public DataResponse<List<PermissionMenuDto>> totalPermissionTree() {
        List<PermissionMenuDto> tree = permissionService.totalPermissionTree();
        return new DataResponse<>(ResponseEnum.SUCCESS, tree);
    }


}
