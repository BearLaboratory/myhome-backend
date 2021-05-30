package pro.dengyi.myhome.servicefrontend.dto;

import lombok.Data;
import pro.dengyi.myhome.myhomemodel.business.MessageLog;

/**
 * @author BLab
 */
@Data
public class UserMessageDto extends MessageLog {

    private String familyName;
    private String fromUserName;
}
