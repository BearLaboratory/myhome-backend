package pro.dengyi.myhome.servicedevice.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pro.dengyi.myhome.common.response.BaseResponse;
import pro.dengyi.myhome.common.response.DataResponse;
import pro.dengyi.myhome.common.response.ResponseEnum;
import pro.dengyi.myhome.myhomemodel.business.device.DeviceCategory;
import pro.dengyi.myhome.servicedevice.service.DeviceCategoryService;
import pro.dengyi.myhome.servicedevice.vo.CategoryPageVo;


/**
 * 设备类型管理controller
 *
 * @author DengYi
 * @version v1.0
 */
@RestController
@RequestMapping("/deviceCategory")
@Api(tags = "设备类型接口")
public class DeviceCategoryController {
    @Autowired
    private DeviceCategoryService deviceCategoryService;


    @ApiOperation("条件分页查询")
    @PostMapping("/page")
    public DataResponse<Page<DeviceCategory>> page(@RequestBody CategoryPageVo vo) {
        Page<DeviceCategory> page = deviceCategoryService.page(vo);
        return new DataResponse<>(ResponseEnum.SUCCESS, page);
    }

    @ApiOperation("新增或修改")
    @PostMapping("/addOrUpdate")
    public BaseResponse addOrUpdate(@RequestBody DeviceCategory deviceCategory) {
        deviceCategoryService.addOrUpdate(deviceCategory);
        return new BaseResponse(ResponseEnum.SUCCESS);
    }

    @ApiOperation("删除分类")
    @PostMapping("/delCategory")
    public BaseResponse delCategory(@RequestBody DeviceCategory deviceCategory) {
        deviceCategoryService.delCategory(deviceCategory);
        return new BaseResponse(ResponseEnum.SUCCESS);
    }

}
