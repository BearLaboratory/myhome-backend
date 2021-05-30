package pro.dengyi.myhome.servicefrontend.service;

import javax.servlet.http.HttpServletRequest;

/**
 * @author DengYi
 * @version v1.0
 */
public interface ValidCodeService {
    void getCodeByPhone(String phoneString, HttpServletRequest request);
}
