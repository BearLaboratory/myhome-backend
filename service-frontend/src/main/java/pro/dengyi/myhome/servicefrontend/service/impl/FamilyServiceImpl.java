package pro.dengyi.myhome.servicefrontend.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pro.dengyi.myhome.common.exception.BusinessException;
import pro.dengyi.myhome.common.response.ResponseEnum;
import pro.dengyi.myhome.myhomemodel.business.MessageLog;
import pro.dengyi.myhome.myhomemodel.business.frontend.Family;
import pro.dengyi.myhome.myhomemodel.business.frontend.FamilyUserMiddle;
import pro.dengyi.myhome.myhomeutil.holder.UserHolder;
import pro.dengyi.myhome.servicefrontend.dao.FamilyDao;
import pro.dengyi.myhome.servicefrontend.dao.FamilyUserMiddleDao;
import pro.dengyi.myhome.servicefrontend.dao.MessageLogDao;
import pro.dengyi.myhome.servicefrontend.dto.FamilyListDto;
import pro.dengyi.myhome.servicefrontend.service.FamilyService;

import java.util.Date;
import java.util.List;

/**
 * @author BLab
 */
@Service
public class FamilyServiceImpl implements FamilyService {
    @Autowired
    private FamilyDao familyDao;
    @Autowired
    private FamilyUserMiddleDao familyUserMiddleDao;
    @Autowired
    private StringRedisTemplate redisTemplate;
    @Autowired
    private MessageLogDao messageLogDao;


    @Override
    @Transactional
    public void addOrUpdate(Family family) {
        String userId = UserHolder.getUserId();
        if (StringUtils.isBlank(family.getId())) {
            //1. 新增
            family.setCreateTime(new Date());
            family.setUpdateTime(new Date());
            family.setRoomCount(0);
            family.setFloorCount(0);
            family.setDeviceCount(0);
            familyDao.insert(family);
            //2. 将户主关系插入中间表
            FamilyUserMiddle middle = new FamilyUserMiddle();
            middle.setFamilyId(family.getId());
            middle.setIsHolder(true);
            middle.setUserId(userId);
            familyUserMiddleDao.insert(middle);
        } else {
            //1. 修改
            Family familySaved = familyDao.selectById(family.getId());
            familySaved.setName(family.getName());
            familySaved.setUpdateTime(new Date());
            familyDao.updateById(familySaved);
            //2. 删除缓存
            redisTemplate.delete("family::" + family.getId());
        }

    }

    @Override
    public List<Family> familyList() {
        String userId = UserHolder.getUserId();
        return familyDao.selectFamilyListByUserId(userId);
    }

    @Transactional
    @Override
    public void confirmJoin(String messageId) {
        MessageLog messageLog = messageLogDao.selectById(messageId);
        if (messageLog == null) {
            throw new BusinessException(ResponseEnum.PARAM_ERROR);
        }
        String userId = UserHolder.getUserId();
        FamilyUserMiddle middleObj = new FamilyUserMiddle();
        middleObj.setUserId(userId);
        middleObj.setFamilyId(messageLog.getFamilyId());
        middleObj.setIsHolder(false);
        familyUserMiddleDao.insert(middleObj);
        //更新缓存
        Family family = familyDao.selectById(messageLog.getFamilyId());
        FamilyListDto familyListDto = new FamilyListDto();
        //BeanUtil.copyProperties(family, familyListDto);
        familyListDto.setCreateTime(null);
        familyListDto.setUpdateTime(null);
        ListOperations listOperations = redisTemplate.opsForList();
        listOperations.rightPush("family::" + userId, familyListDto);

    }

    @Override
    public Boolean checkIsHolder(String familyId) {
        String userId = UserHolder.getUserId();
        QueryWrapper<FamilyUserMiddle> qr = new QueryWrapper<>();
        qr.eq("user_id", userId);
        qr.eq("family_id", familyId);
        FamilyUserMiddle familyUserMiddle = familyUserMiddleDao.selectOne(qr);
        return familyUserMiddle.getIsHolder();
    }
}
