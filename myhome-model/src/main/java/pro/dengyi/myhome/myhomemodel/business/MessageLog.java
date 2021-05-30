package pro.dengyi.myhome.myhomemodel.business;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.util.Date;

/**
 * @author BLab
 */
@Data
@TableName("t_message_log")
@ApiModel("消息记录")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MessageLog {
    private String id;
    private String fromUserId;
    private String toUserId;
    private String familyId;
    private Boolean hasRead;
    //1.家庭添加用户
    private Integer type;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;
}
