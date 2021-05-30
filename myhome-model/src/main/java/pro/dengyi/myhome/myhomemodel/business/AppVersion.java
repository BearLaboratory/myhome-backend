package pro.dengyi.myhome.myhomemodel.business;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * app版本
 *
 * @author BLab
 */
@Data
@TableName("t_app_version")
@ApiModel("app版本")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AppVersion {

    @ApiModelProperty(value = "id")
    private String id;

    @ApiModelProperty(value = "更新类型，1整包--2热更新")
    private Integer type;

    @ApiModelProperty(value = "版本名，如1.0.0")
    private String versionName;

    @ApiModelProperty(value = "版本编码，如101")
    private Integer versionCode;

    @ApiModelProperty(value = "版本描述")
    private String description;

    @ApiModelProperty(value = "下载地址")
    private String downloadUrl;

    @ApiModelProperty(value = "发布时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
}
