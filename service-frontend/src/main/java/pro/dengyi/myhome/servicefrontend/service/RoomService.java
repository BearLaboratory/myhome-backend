package pro.dengyi.myhome.servicefrontend.service;


import pro.dengyi.myhome.myhomemodel.business.frontend.Room;

import java.util.List;

/**
 * @author BLab
 */
public interface RoomService {
    /**
     * 新增或修改房间
     *
     * @param room 房间实体
     */
    void addOrUpdate(Room room);

    /**
     * 根据楼层ID查询所有房间集合
     *
     * @param floorId 楼层ID
     * @return
     */
    List<Room> list(String floorId);

    /**
     * 查询家庭里的所有房间列表
     *
     * @param familyId 家庭ID
     * @return
     */
    List<Room> listAllByFamilyId(String familyId);

}
