package pro.dengyi.myhome.servicegateway.apis;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Primary;
import org.springframework.web.bind.annotation.GetMapping;
import pro.dengyi.myhome.servicegateway.apis.impl.BackendApiImpl;

import java.util.List;

/**
 * 用户中心api
 *
 * @author DengYi
 * @version v1.0
 */
@Primary
@FeignClient(value = "SERVICE-BACKEND", fallback = BackendApiImpl.class)
public interface BackendApi {

    /**
     * 查询用户所有权限List集合，只查询集合
     *
     * @return
     */
    @GetMapping("/backend/sysUser/getAllPermissionsList")
    List<String> getAllPermissionList();


}
