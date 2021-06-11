package pro.dengyi.myhome.servicefrontend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import pro.dengyi.myhome.common.exception.BusinessException;
import pro.dengyi.myhome.common.response.ResponseEnum;
import pro.dengyi.myhome.myhomemodel.business.frontend.Family;
import pro.dengyi.myhome.myhomemodel.business.frontend.FamilyUserMiddle;
import pro.dengyi.myhome.myhomemodel.business.frontend.User;
import pro.dengyi.myhome.myhomeutil.UserTokenUtil;
import pro.dengyi.myhome.myhomeutil.holder.UserHolder;
import pro.dengyi.myhome.myhomeutil.holder.UserHolderModel;
import pro.dengyi.myhome.servicefrontend.dao.FamilyDao;
import pro.dengyi.myhome.servicefrontend.dao.FamilyUserMiddleDao;
import pro.dengyi.myhome.servicefrontend.dao.MessageLogDao;
import pro.dengyi.myhome.servicefrontend.dao.UserDao;
import pro.dengyi.myhome.servicefrontend.dto.UserInfoDto;
import pro.dengyi.myhome.servicefrontend.service.UserService;
import pro.dengyi.myhome.servicefrontend.vo.PhoneLoginVo;

/**
 * @author BLab
 */
@Service
public class UserServiceImpl implements UserService {
    private static final String DEFAULT_AVATAR = "https://image.dengyi.pro/dengyi/1613733826939384.jpg";
    @Autowired
    private UserDao userDao;
    @Autowired
    private FamilyDao familyDao;
    @Autowired
    private FamilyUserMiddleDao familyUserMiddleDao;
    @Autowired
    private MessageLogDao messageLogDao;
    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public String loginByPhone(PhoneLoginVo vo) {
        //todo 本地测试时注释，默认验证码时123456
//        String sendCode = (String) redisTemplate.opsForValue().get("validCode::" + vo.getPhone());
        String sendCode = "123456";
        if (StringUtils.isBlank(sendCode)) {
            throw new BusinessException(ResponseEnum.CODE_EXPIRE);
        }
        if (!sendCode.equals(vo.getValidCode())) {
            throw new BusinessException(ResponseEnum.CODE_ERROR);
        }

        //如果手机号存在就登录，如果不存在就新增
        User phoneUser = userDao.selectOne(
                new LambdaQueryWrapper<User>()
                        .eq(User::getPhone, vo.getPhone())
        );
        UserHolderModel userHolderModel = new UserHolderModel();
        if (phoneUser == null) {
            //用户不存在，新增用户。同时设置默认值
            User newUser = new User();
            newUser.setPhone(vo.getPhone());
            //设置默认值
            newUser.setAvatar(DEFAULT_AVATAR);
            newUser.setName("用户" + vo.getPhone().substring(6));
            userDao.insert(newUser);
            userHolderModel.setId(newUser.getId());
        } else {
            userHolderModel.setId(phoneUser.getId());
        }
        return UserTokenUtil.genToken(userHolderModel);
    }


    @Override
    public UserInfoDto getUserInfo() {
        UserInfoDto userInfoDto = new UserInfoDto();
        //1. 查询用户信息
        User user = userDao.selectById(UserHolder.getUserId());
        user.setPhone(null);
        user.setPassword(null);
        user.setOpenId(null);
        userInfoDto.setUser(user);
        //2. 查询家庭信息
        if (StringUtils.isNotBlank(user.getSelectedFamilyId())) {
            Family family = familyDao.selectById(user.getSelectedFamilyId());
            userInfoDto.setFamily(family);
            QueryWrapper<FamilyUserMiddle> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("family_id", user.getSelectedFamilyId());
            queryWrapper.eq("user_id", UserHolder.getUserId());
            FamilyUserMiddle familyUserMiddle = familyUserMiddleDao.selectOne(queryWrapper);
            userInfoDto.setIsHolder(familyUserMiddle.getIsHolder() != null && familyUserMiddle.getIsHolder());
        }
        return userInfoDto;
    }


//    @Override
//    public Boolean searchUserByPhone(String phone) {
//        String userId = UserHolder.getUserId();
//        User selectById = userDao.selectById(userId);
//        //不能搜索自己
//        if (selectById.getPhone().equalsIgnoreCase(phone)) {
//            return false;
//        }
//        QueryWrapper<User> qr = new QueryWrapper<>();
//        qr.eq("phone", phone);
//        return userDao.selectOne(qr) != null;
//    }
//
//    @Override
//    public void addUser2Family(HashMap<String, String> addParam) {
//        //查询消息是否发送，同时是否处理，发送了则不在添加，处理了也不添加
//        //通知内容：XXX邀请你加入XXX家庭
//
//    }
//
//    @Override
//    public String loginByPhonePassword(LoginByPhonePasswordVo vo) {
//        QueryWrapper<User> qr = new QueryWrapper<>();
//        qr.eq("phone", vo.getPhone());
//        User user = userDao.selectOne(qr);
//
////        if (user == null) {
////            throw new BusinessException(ResponseEnum.USER_NOT_EXIST);
////        }
////        if (PasswordUtil.match(vo.getPassword(), user.getPassword())) {
////            return UserTokenUtil.genToken(user);
////        } else {
////            throw new BusinessException(ResponseEnum.USERNAME_PASSWORD_ERROR);
////        }
//        return null;
//    }
//

//
//    @Transactional
//    @Override
//    public String registByPhonePassword(RegistByPhonePasswordVo vo) {
//        QueryWrapper<User> qr = new QueryWrapper<>();
//        qr.eq("phone", vo.getPhone());
//        User user = userDao.selectOne(qr);
//        if (user != null) {
//            throw new BusinessException(ResponseEnum.USER_EXIST);
//        }
//        User u = new User();
//        u.setName(vo.getPhone());
//        u.setPhone(vo.getPhone());
//        //u.setPassword(PasswordUtil.encodePassword(vo.getPassword()));
//        u.setAvatar(DEFAULT_AVATAR);
//        userDao.insert(u);
//        //return UserTokenUtil.genToken(u);
//        return null;
//    }
//
//
////    @Transactional
////    @Override
////    public void updateUserInfo(User user) {
////        User userOri = userDao.selectById(UserHolder.getUserId());
////        if (StringUtils.isNotBlank(user.getName())) {
////            userOri.setName(user.getName());
////        }
////        if (StringUtils.isNotBlank(user.getAvatar())) {
////            userOri.setAvatar(user.getAvatar());
////        }
////        userDao.updateById(userOri);
////    }
//
//    @Override
//    public void userAppOnline(AppOnlineVo vo) {
//        HashOperations hashOperations = redisTemplate.opsForHash();
//        if (vo.getStatus()) {
//            hashOperations.put("onlineApp::" + vo.getFamilyId(), vo.getUserId(), true);
//        } else {
//            hashOperations.delete("onlineApp::" + vo.getFamilyId(), vo.getUserId());
//        }
//
//    }
//
//
////    @Transactional
////    @Override
////    public void updateUserSelected(User user) {
////        User userSaved = userDao.selectById(UserHolder.getUserId());
////        if (StringUtils.isNotBlank(user.getSelectedFamilyId())) {
////            userSaved.setSelectedFamilyId(user.getSelectedFamilyId());
////        }
////        if (StringUtils.isNotBlank(user.getSelectedFloorId())) {
////            userSaved.setSelectedFloorId(user.getSelectedFloorId());
////        }
////        userDao.updateById(userSaved);
////    }
//
//    @Override
//    public Boolean getHaveNotRead() {
//        QueryWrapper<MessageLog> qr = new QueryWrapper<>();
//        qr.eq("to_user_id", UserHolder.getUserId());
//        qr.eq("has_read", false);
//        List<MessageLog> messageLogs = messageLogDao.selectList(qr);
//        return !CollectionUtils.isEmpty(messageLogs);
//    }


}
