package pro.dengyi.myhome.servicebackend.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import pro.dengyi.myhome.myhomemodel.business.backend.User;
import pro.dengyi.myhome.servicebackend.dto.UserDto;
import pro.dengyi.myhome.servicebackend.vo.PageVo;

/**
 * @author DengYi
 * @version v1.0
 */
@Repository
public interface UserDao extends BaseMapper<User> {
    IPage<UserDto> customPage(IPage<UserDto> iPage, @Param("pageVo") PageVo pageVo);
}
