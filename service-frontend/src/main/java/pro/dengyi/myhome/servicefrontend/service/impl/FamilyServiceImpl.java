package pro.dengyi.myhome.servicefrontend.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import pro.dengyi.myhome.common.exception.BusinessException;
import pro.dengyi.myhome.common.response.ResponseEnum;
import pro.dengyi.myhome.myhomemodel.business.Family;
import pro.dengyi.myhome.myhomemodel.business.FamilyUserMiddle;
import pro.dengyi.myhome.myhomemodel.business.MessageLog;
import pro.dengyi.myhome.myhomeutil.holder.UserHolder;
import pro.dengyi.myhome.servicefrontend.dao.FamilyDao;
import pro.dengyi.myhome.servicefrontend.dao.FamilyUserMiddleDao;
import pro.dengyi.myhome.servicefrontend.dao.MessageLogDao;
import pro.dengyi.myhome.servicefrontend.dto.FamilyListDto;
import pro.dengyi.myhome.servicefrontend.service.FamilyService;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    private RedisTemplate redisTemplate;
    @Autowired
    private MessageLogDao messageLogDao;


    @Override
    @Transactional
    public void addOrUpdate(Family family) {
        String userId = UserHolder.getUserId();
        ListOperations listOperations = redisTemplate.opsForList();
        if (StringUtils.isBlank(family.getId())) {
            //新增
            family.setCreateTime(new Date());
            family.setUpdateTime(new Date());
            familyDao.insert(family);
            FamilyUserMiddle middle = new FamilyUserMiddle();
            middle.setFamilyId(family.getId());
            middle.setIsHolder(true);
            middle.setUserId(userId);
            familyUserMiddleDao.insert(middle);
            //新增缓存
            FamilyListDto familyListDto = new FamilyListDto();
            //BeanUtil.copyProperties(family, familyListDto);
            familyListDto.setDeviceCount(0);
            familyListDto.setFloorCount(0);
            familyListDto.setRoomCount(0);
            familyListDto.setCreateTime(null);
            familyListDto.setUpdateTime(null);
            listOperations.leftPush("family::" + userId, familyListDto);
        } else {
            //修改
            Family familySaved = familyDao.selectById(family.getId());
            familySaved.setName(family.getName());
            familySaved.setUpdateTime(new Date());
            familyDao.updateById(familySaved);
            //更新缓存
            List cacheFamilyList = listOperations.range("family::" + userId, 0, -1);
            for (Object familyCached : cacheFamilyList) {

                Map<String, String> obj = (Map<String, String>) familyCached;
                if (familySaved.getId().equals(obj.get("id"))) {
                    obj.put("name", family.getName());
                    redisTemplate.delete("family::" + userId);
                    listOperations.rightPushAll("family::" + userId, cacheFamilyList);
                }
            }
        }

    }

    @Override
    public List<FamilyListDto> familyList() {
        String userId = UserHolder.getUserId();
        ListOperations listOperations = redisTemplate.opsForList();
        List cacheFamilyList = listOperations.range("family::" + userId, 0, -1);
        if (CollectionUtils.isEmpty(cacheFamilyList)) {
            Map<String, String> param = new HashMap<>(1);
            param.put("userId", userId);
            List<FamilyListDto> familyListDtos = familyDao.selectFamilyList(param);
            if (!CollectionUtils.isEmpty(familyListDtos)) {
                listOperations.rightPushAll("family::" + userId, familyListDtos);
            }
            return familyListDtos;
        } else {
            return cacheFamilyList;
        }
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
    public Boolean checkIsHoler(String familyId) {
        String userId = UserHolder.getUserId();
        QueryWrapper<FamilyUserMiddle> qr = new QueryWrapper<>();
        qr.eq("user_id", userId);
        qr.eq("family_id", familyId);
        FamilyUserMiddle familyUserMiddle = familyUserMiddleDao.selectOne(qr);
        return familyUserMiddle.getIsHolder();
    }
}
