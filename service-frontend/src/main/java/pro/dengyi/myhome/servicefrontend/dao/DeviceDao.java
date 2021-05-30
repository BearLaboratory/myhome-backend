package pro.dengyi.myhome.servicefrontend.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import pro.dengyi.myhome.myhomemodel.business.Device;

import java.util.HashMap;
import java.util.List;

/**
 * @author BLab
 */
@Repository
public interface DeviceDao extends BaseMapper<Device> {

    /**
     * 查询设备信息以便于设备上报处理
     *
     * @param deviceId
     * @return
     */
    Device selectByIdForReport(@Param("deviceId") String deviceId);

    /**
     * 根据家庭ID房间ID是否喜欢查询设备列表
     *
     * @param params
     * @return
     */
    List<Device> selectListByFidRidFa(@Param("params") HashMap<String, Object> params);
}
