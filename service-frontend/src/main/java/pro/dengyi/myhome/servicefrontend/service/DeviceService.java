package pro.dengyi.myhome.servicefrontend.service;


import pro.dengyi.myhome.myhomemodel.business.device.Device;

import java.util.List;

/**
 * @author BLab
 */
public interface DeviceService {
    /**
     * 根据房间ID查询房间所有设备
     *
     * @param familyId 家庭ID
     * @param roomId   房间ID
     * @param favorite 是否收藏
     * @return
     */
    List<Device> listByRoomId(String familyId, String roomId, Boolean favorite);

    /**
     * 改变设备的收藏状态
     *
     * @param device 设备数据封装实体
     */
    void changeFavorite(Device device);

    /**
     * 控制设备
     *
     * @param device
     */
    void doControl(Device device);

    /**
     * 用户添加设备
     *
     * @param device
     */
    void addDevice(Device device);
}
