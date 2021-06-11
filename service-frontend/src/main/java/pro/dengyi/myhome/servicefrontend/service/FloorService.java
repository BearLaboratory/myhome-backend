package pro.dengyi.myhome.servicefrontend.service;


import pro.dengyi.myhome.myhomemodel.business.frontend.Floor;

import java.util.List;

/**
 * @author BLab
 */
public interface FloorService {
    /**
     * 新增或更新楼层信息
     * 最多限制添加10层
     *
     * @param floor 楼层数据实体
     */
    void addOrUpdate(Floor floor);

    /**
     * 查询所有楼层列表，一个人的楼层不会很多，所以直接查出所有
     *
     * @param familyId 家庭ID
     * @return List<Floor>
     */
    List<Floor> listByFamilyId(String familyId);

    /**
     * 删除楼层
     * <p>
     * 如果楼层下有房间则不能删除
     *
     * @param floor 楼层
     */
    void deleteFloor(Floor floor);
}
