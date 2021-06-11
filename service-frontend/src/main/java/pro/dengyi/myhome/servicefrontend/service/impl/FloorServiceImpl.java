package pro.dengyi.myhome.servicefrontend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pro.dengyi.myhome.common.exception.BusinessException;
import pro.dengyi.myhome.common.response.ResponseEnum;
import pro.dengyi.myhome.myhomemodel.business.frontend.Family;
import pro.dengyi.myhome.myhomemodel.business.frontend.Floor;
import pro.dengyi.myhome.myhomemodel.business.frontend.Room;
import pro.dengyi.myhome.servicefrontend.dao.FamilyDao;
import pro.dengyi.myhome.servicefrontend.dao.FloorDao;
import pro.dengyi.myhome.servicefrontend.dao.RoomDao;
import pro.dengyi.myhome.servicefrontend.service.FloorService;

import java.util.Date;
import java.util.List;

/**
 * @author BLab
 */
@Service
public class FloorServiceImpl implements FloorService {
    @Autowired
    private FloorDao floorDao;
    @Autowired
    private FamilyDao familyDao;
    @Autowired
    private RoomDao roomDao;


    @Override
    @Transactional
    public void addOrUpdate(Floor floor) {
        if (StringUtils.isBlank(floor.getId())) {
            //1. 新增楼层,暂时不限制楼层数
            floor.setRoomCount(0);
            floor.setDeviceCount(0);
            floor.setCreateTime(new Date());
            floor.setUpdateTime(new Date());
            floorDao.insert(floor);
            //2. 家庭楼层数加一
            Family family = familyDao.selectById(floor.getFamilyId());
            family.setFloorCount(family.getFloorCount() + 1);
            familyDao.updateById(family);

        } else {
            //修改楼层
            floor.setUpdateTime(new Date());
            floorDao.updateById(floor);
        }

    }

    @Override
    public List<Floor> listByFamilyId(String familyId) {
        //todo 缓存
        List<Floor> floors = floorDao.selectList(
                new LambdaQueryWrapper<Floor>()
                        .eq(Floor::getFamilyId, familyId)
                        .orderByDesc(Floor::getCreateTime)
        );

        return floors;

    }

    @Override
    @Transactional
    public void deleteFloor(Floor floor) {
        Integer roomCount = roomDao.selectCount(
                new LambdaQueryWrapper<Room>()
                        .eq(Room::getFloorId, floor.getId())
        );
        if (roomCount != null && roomCount != 0) {
            throw new BusinessException(ResponseEnum.HAVA_SUB_ROOMS);
        }
        //可以删除
        floorDao.deleteById(floor.getId());
        //更新家庭楼层统计
        Family family = familyDao.selectById(floor.getFamilyId());
        family.setFloorCount(family.getFloorCount() - 1);
        familyDao.updateById(family);
    }
}
