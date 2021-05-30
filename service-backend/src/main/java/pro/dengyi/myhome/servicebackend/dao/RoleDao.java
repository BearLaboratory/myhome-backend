package pro.dengyi.myhome.servicebackend.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;
import pro.dengyi.myhome.myhomemodel.business.backend.Role;

/**
 * @author DengYi
 * @version v1.0
 */
@Repository
public interface RoleDao extends BaseMapper<Role> {
}
