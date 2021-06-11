package pro.dengyi.myhome.myhomemodel.business.device;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonRawValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.Date;

/**
 * @author BLab
 */
@Data
@TableName("device")
@ApiModel("设备")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Device {

    @ApiModelProperty(value = "设备ID")
    private String id;

    @ApiModelProperty(value = "设备名")
    @NotBlank
    private String name;

    @ApiModelProperty(value = "设备类型")
    @TableField(exist = false)
    private Integer type;

    @ApiModelProperty(value = "二级类型")
    @TableField(exist = false)
    private Integer subType;

    @ApiModelProperty(value = "数据格式")
    @TableField(exist = false)
    private String dataFormat;

    @ApiModelProperty(value = "开启")
    private Boolean open;

    @ApiModelProperty(value = "是否收藏")
    private Boolean favorite;

    @ApiModelProperty(value = "图标")
    private String iconPath;

    @ApiModelProperty(value = "所属房间ID")
    private String roomId;


    @ApiModelProperty(value = "所属房间ID")
    private String familyId;

    @ApiModelProperty(value = "是否在线")
    private Boolean online;

    @ApiModelProperty(value = "是否发布")
    private Boolean publish;

    @ApiModelProperty(value = "所属分类ID")
    private String categoryId;

    @ApiModelProperty(value = "开发者ID")
    private String developerId;

    @ApiModelProperty(value = "设备密钥")
    private String deviceSecret;

    @JsonRawValue
    private String payload;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;

}
