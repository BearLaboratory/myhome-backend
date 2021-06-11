package pro.dengyi.myhome.servicefrontend.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;
import pro.dengyi.myhome.myhomemodel.business.MessageLog;

/**
 * @author BLab
 */
@Repository
public interface MessageLogDao extends BaseMapper<MessageLog> {
//    List<UserMessageDto> selectMessageList(@Param("userId") String userId);
}
