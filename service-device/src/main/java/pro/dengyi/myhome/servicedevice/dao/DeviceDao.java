package pro.dengyi.myhome.servicedevice.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import pro.dengyi.myhome.myhomemodel.business.device.Device;

import java.util.HashMap;

/**
 * @author DengYi
 * @version v1.0
 */
@Repository
public interface DeviceDao extends BaseMapper<Device> {
    Page<Device> customPage(Page<Device> page, @Param("params") HashMap<String, Object> params);
}
