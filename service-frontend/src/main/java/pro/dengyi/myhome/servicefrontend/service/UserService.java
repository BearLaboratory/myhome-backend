package pro.dengyi.myhome.servicefrontend.service;


import pro.dengyi.myhome.servicefrontend.dto.UserInfoDto;
import pro.dengyi.myhome.servicefrontend.vo.PhoneLoginVo;

import javax.servlet.http.HttpServletRequest;

/**
 * @author BLab
 */
public interface UserService {
//    /**
//     * 根据手机号查询用户是否存在
//     *
//     * @param phone 用户手机号
//     * @return
//     */
//    Boolean searchUserByPhone(String phone);
//
//    /**
//     * 将用户添加到家庭
//     *
//     * @param addParam 添加参数
//     */
//    void addUser2Family(HashMap<String, String> addParam);
//
//    /**
//     * 手机号密码登录
//     *
//     * @param vo 数据封装实体
//     * @return token
//     */
//    String loginByPhonePassword(LoginByPhonePasswordVo vo);
//

//
//    /**
//     * 手机好密码注册
//     *
//     * @param vo
//     * @return
//     */
//    String registByPhonePassword(RegistByPhonePasswordVo vo);
//
//    /**
//     * 修改用户信息
//     *
//     * @param user
//     */
//    void updateUserInfo(User user);
//
//    /**
//     * 用户APP在线
//     *
//     * @param vo
//     */
//    void userAppOnline(AppOnlineVo vo);
//
//    /**
//     * 上报用户选择了的家庭或者房间
//     *
//     * @param user
//     */
//    void updateUserSelected(User user);
//
//    /**
//     * 查询是否有未读消息
//     *
//     * @return
//     */
//    Boolean getHaveNotRead();

    /**
     * 手机号验证码登录
     *
     * @param vo
     * @param request
     * @return
     */
    String loginByPhone(PhoneLoginVo vo, HttpServletRequest request);


    /**
     * 查询用户信息
     *
     * @return
     */
    UserInfoDto getUserInfo();
}
