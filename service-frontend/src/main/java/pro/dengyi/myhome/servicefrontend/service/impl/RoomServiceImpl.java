package pro.dengyi.myhome.servicefrontend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pro.dengyi.myhome.common.exception.BusinessException;
import pro.dengyi.myhome.common.response.ResponseEnum;
import pro.dengyi.myhome.myhomemodel.business.frontend.Floor;
import pro.dengyi.myhome.myhomemodel.business.frontend.Room;
import pro.dengyi.myhome.servicefrontend.apis.DeviceApi;
import pro.dengyi.myhome.servicefrontend.dao.FloorDao;
import pro.dengyi.myhome.servicefrontend.dao.RoomDao;
import pro.dengyi.myhome.servicefrontend.service.RoomService;

import java.util.Date;
import java.util.List;

/**
 * @author BLab
 */
@Service
public class RoomServiceImpl implements RoomService {
    @Autowired
    private RoomDao roomDao;
    @Autowired
    private FloorDao floorDao;
    @Autowired
    private DeviceApi deviceApi;


    @Override
    @Transactional
    public void addOrUpdate(Room room) {
        if (StringUtils.isBlank(room.getId())) {
            //1. 新增,同时将设备总数置为0
            room.setDeviceCount(0);
            room.setCreateTime(new Date());
            room.setUpdateTime(new Date());
            roomDao.insert(room);
            //2. 增加楼层房间数量
            Floor floor = floorDao.selectById(room.getFloorId());
            floor.setRoomCount(floor.getRoomCount() + 1);
            floorDao.updateById(floor);
        } else {
            room.setUpdateTime(new Date());
            roomDao.updateById(room);
        }
    }

    @Override
    public List<Room> list(String floorId) {

        List<Room> roomList = roomDao.selectList(
                new LambdaQueryWrapper<Room>()
                        .eq(Room::getFloorId, floorId)
                        .orderByDesc(Room::getCreateTime)
        );

        return roomList;
    }

    @Override
    public List<Room> listAllByFamilyId(String familyId) {
        List<Room> roomList = roomDao.selectList(
                new LambdaQueryWrapper<Room>()
                        .eq(Room::getFamilyId, familyId)
                        .orderByDesc(Room::getCreateTime)
        );
        return roomList;
    }

    @Override
    @Transactional
    public void deleteRoom(Room room) {
        Boolean canDeleteFlag = deviceApi.roomContainDevices(room.getId());
        if (canDeleteFlag) {
            throw new BusinessException(ResponseEnum.ROOM_CONTAIN_DEVICE);
        }
        roomDao.deleteById(room.getId());
        //更新楼层房间总数
        Floor floor = floorDao.selectById(room.getFloorId());
        floor.setRoomCount(floor.getRoomCount() - 1);
        floorDao.updateById(floor);
    }
}
