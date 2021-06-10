package pro.dengyi.myhome.myhomemodel.business.frontend;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * 楼层实体
 *
 * @author BLab
 */
@Data
@TableName("room")
@ApiModel("房间")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Room {

    @ApiModelProperty(value = "Id")
    private String id;

    @ApiModelProperty(value = "房间名")
    private String name;

    @ApiModelProperty(value = "楼层ID")
    private String floorId;

    @ApiModelProperty(value = "家庭ID")
    private String familyId;

    @ApiModelProperty(value = "设备总数")
    private Integer deviceCount;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    private Date updateTime;
}
