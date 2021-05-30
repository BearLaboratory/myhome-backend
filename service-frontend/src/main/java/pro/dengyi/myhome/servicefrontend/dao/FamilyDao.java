package pro.dengyi.myhome.servicefrontend.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import pro.dengyi.myhome.myhomemodel.business.Family;
import pro.dengyi.myhome.servicefrontend.dto.FamilyListDto;


import java.util.List;
import java.util.Map;

/**
 * @author BLab
 */
@Repository
public interface FamilyDao extends BaseMapper<Family> {
    /**
     * 查询用户所有家庭列表
     *
     * @param param 参数
     * @return
     */
    List<FamilyListDto> selectFamilyList(@Param("param") Map<String, String> param);

}
