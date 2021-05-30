package pro.dengyi.myhome.servicefrontend.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pro.dengyi.myhome.common.response.DataResponse;
import pro.dengyi.myhome.common.response.ResponseEnum;
import pro.dengyi.myhome.myhomemodel.business.AppVersion;
import pro.dengyi.myhome.servicefrontend.service.AppVersionService;


/**
 * @author BLab
 */
@Api(tags = "app版本")
@RestController
@RequestMapping("/appVersion")
public class AppVersionController {
    @Autowired
    private AppVersionService appVersionService;


    @ApiOperation("查询最新的APP版本信息，APP端自行根据versionCode判断是否需要升级")
    @GetMapping("/getNewestApp")
    public DataResponse<AppVersion> getNewestApp() {
        AppVersion appVersion = appVersionService.getNewestApp();
        return new DataResponse<>(ResponseEnum.SUCCESS, appVersion);
    }


}
