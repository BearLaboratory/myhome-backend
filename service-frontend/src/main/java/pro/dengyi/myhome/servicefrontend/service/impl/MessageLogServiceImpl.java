package pro.dengyi.myhome.servicefrontend.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pro.dengyi.myhome.servicefrontend.dao.MessageLogDao;
import pro.dengyi.myhome.servicefrontend.dao.UserDao;
import pro.dengyi.myhome.servicefrontend.service.MessageLogService;

/**
 * @author BLab
 */
@Service
public class MessageLogServiceImpl implements MessageLogService {
    @Autowired
    private MessageLogDao messageLogDao;
    @Autowired
    private UserDao userDao;


//    @Override
//    @Transactional
//    public void sendFamilyAddUser(FamilyAddUserVo vo) {
//        String userId = UserHolder.getUserId();
//        MessageLog messageLog = new MessageLog();
//        messageLog.setFromUserId(userId);
//        QueryWrapper<User> qr = new QueryWrapper<>();
//        qr.eq("phone", vo.getToUserPhone());
//        User user = userDao.selectOne(qr);
//        if (user == null) {
//            throw new BusinessException(ResponseEnum.PARAM_ERROR);
//        }
//        messageLog.setToUserId(user.getId());
//        messageLog.setFamilyId(vo.getFamilyId());
//        messageLog.setType(1);
//        messageLog.setHasRead(false);
//        messageLog.setCreateTime(new Date());
//        messageLog.setUpdateTime(new Date());
//        messageLogDao.insert(messageLog);
//    }

//    @Override
//    public List<UserMessageDto> getAllMessage() {
//        String userId = UserHolder.getUserId();
//        return messageLogDao.selectMessageList(userId);
//    }
//
//    @Transactional
//    @Override
//    public void readMessage(String messageId) {
//        MessageLog messageLog = messageLogDao.selectById(messageId);
//        messageLog.setHasRead(true);
//        messageLogDao.updateById(messageLog);
//    }
}
