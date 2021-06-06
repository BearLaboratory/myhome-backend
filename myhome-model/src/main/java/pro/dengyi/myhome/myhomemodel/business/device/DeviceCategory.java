package pro.dengyi.myhome.myhomemodel.business.device;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonRawValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * 设备类型实体
 *
 * @author DengYi
 * @version v1.0
 */
@Data
@TableName("device_category")
@ApiModel("设备分类实体")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DeviceCategory {

    @ApiModelProperty(value = "设备Id")
    private String id;

    @ApiModelProperty(value = "设备类型名")
    private String name;

    @ApiModelProperty(value = "设备主类型,1控制型2测量型")
    private Integer type;

    @ApiModelProperty(value = "设备次类型")
    private Integer subType;

    @ApiModelProperty(value = "数据格式")
    @TableField(typeHandler = JacksonTypeHandler.class)
    private Object dataFormat;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    private Date updateTime;
}
