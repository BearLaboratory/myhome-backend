package pro.dengyi.myhome.servicefrontend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pro.dengyi.myhome.myhomemodel.business.Room;
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

    @Override
    @Transactional
    public void addOrUpdate(Room room) {
        if (StringUtils.isBlank(room.getId())) {
            //新增
            room.setCreateTime(new Date());
            room.setUpdateTime(new Date());
            roomDao.insert(room);
        } else {
            room.setUpdateTime(new Date());
            roomDao.updateById(room);
        }
    }

    @Override
    public List<Room> list(String floorId) {
        QueryWrapper<Room> qr = new QueryWrapper<>();
        qr.eq("floor_id", floorId);
        qr.orderByAsc("create_time");
        return roomDao.selectList(qr);
    }

    @Override
    public List<Room> listAllByFamilyId(String familyId) {
        QueryWrapper<Room> qr = new QueryWrapper<>();
        qr.eq("family_id", familyId);
        return roomDao.selectList(qr);
    }
}
