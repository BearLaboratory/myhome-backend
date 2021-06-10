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
@TableName("t_floor")
@ApiModel("楼层")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Floor {

    @ApiModelProperty(value = "ID")
    private String id;

    @ApiModelProperty(value = "楼层名")
    private String name;

    @ApiModelProperty(value = "家庭ID")
    private String familyId;

    @ApiModelProperty(value = "房间总数")
    private Integer roomCount;

    @ApiModelProperty(value = "设备总数")
    private Integer deviceCount;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    private Date updateTime;
}
