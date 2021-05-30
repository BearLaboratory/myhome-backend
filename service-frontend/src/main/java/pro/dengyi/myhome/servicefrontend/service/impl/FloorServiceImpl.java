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
import pro.dengyi.myhome.myhomemodel.business.Floor;
import pro.dengyi.myhome.servicefrontend.dao.FloorDao;
import pro.dengyi.myhome.servicefrontend.service.FloorService;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * @author BLab
 */
@Service
public class FloorServiceImpl implements FloorService {
    @Autowired
    private FloorDao floorDao;
    private static int maxFloor = 10;
    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    @Transactional
    public void addOrUpdate(Floor floor) {
        // String userId = UserHolder.getUserId();
        ListOperations listOperations = redisTemplate.opsForList();
        if (StringUtils.isBlank(floor.getId())) {
            QueryWrapper<Floor> qr = new QueryWrapper<>();
           // qr.eq("family_id", userId);
            Integer floorNumber = floorDao.selectCount(qr);
            if (floorNumber >= maxFloor) {
                throw new BusinessException(ResponseEnum.PARAM_ERROR);
            }
            floor.setCreateTime(new Date());
            floor.setUpdateTime(new Date());
            floorDao.insert(floor);
            floor.setUpdateTime(null);
            floor.setUpdateTime(null);
            listOperations.leftPush("floors::" + floor.getFamilyId(), floor);
        } else {
            //修改楼层
            floor.setUpdateTime(new Date());
            floorDao.updateById(floor);
            List rangeList = listOperations.range("floors::" + floor.getFamilyId(), 0, -1);
            for (Object obj : rangeList) {
                HashMap hashMap = (HashMap) obj;
                if (hashMap.get("id").equals(floor.getId())) {
                    hashMap.put("name", floor.getName());
                }
            }
            redisTemplate.delete("floors::" + floor.getFamilyId());
            listOperations.rightPushAll("floors::" + floor.getFamilyId(), rangeList);
        }

    }

    @Override
    public List<Floor> list(String familyId) {
        ListOperations listOperations = redisTemplate.opsForList();
        List rangeList = listOperations.range("floors::" + familyId, 0, -1);
        if (CollectionUtils.isEmpty(rangeList)) {
            QueryWrapper<Floor> qr = new QueryWrapper<>();
            qr.eq("family_id", familyId);
            qr.orderByAsc("create_time");
            qr.select("id", "name", "family_id");
            List<Floor> floorList = floorDao.selectList(qr);
            if (!CollectionUtils.isEmpty(floorList)) {
                listOperations.leftPushAll("floors::" + familyId, floorList);
            }
            return floorList;
        } else {
            return rangeList;
        }


    }
}
