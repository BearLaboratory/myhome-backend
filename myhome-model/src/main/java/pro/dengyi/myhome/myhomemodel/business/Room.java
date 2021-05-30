package pro.dengyi.myhome.myhomemodel.business;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.util.Date;

/**
 * 楼层实体
 *
 * @author BLab
 */
@Data
@TableName("t_room")
@ApiModel("房间")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Room {

    private String id;
    private String name;
    private String floorId;
    private String familyId;
    private Date createTime;
    private Date updateTime;
}
