package pro.dengyi.myhome.servicegateway.apis.impl;


import org.springframework.stereotype.Component;
import pro.dengyi.myhome.common.exception.BusinessException;
import pro.dengyi.myhome.common.response.ResponseEnum;
import pro.dengyi.myhome.servicegateway.apis.BackendApi;

import java.util.List;

/**
 * @author DengYi
 * @version v1.0
 */
@Component
public class BackendApiImpl implements BackendApi {
    @Override
    public List<String> getAllPermissionList() {
        throw new BusinessException(ResponseEnum.SERVICE_CALL_ERROR);
    }
}
