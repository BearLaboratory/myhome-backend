package pro.dengyi.myhome.myhomemodel.business.backend;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @author BLab
 */
@Data
@TableName("sys_user")
@ApiModel("系统管理员")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class User {

    @ApiModelProperty("主键")
    private String id;

    @ApiModelProperty("昵称")
    private String name;

    @ApiModelProperty("头像")
    private String avatar;

    @ApiModelProperty("手机号")
    private String phone;

    @ApiModelProperty("密码")
    private String password;

    @ApiModelProperty("邮箱")
    private String email;

    @ApiModelProperty("是否是平台管理员")
    private Boolean isPlatformManager;

    @ApiModelProperty("是否是平台管理员")
    private Boolean isDeveloper;

    @ApiModelProperty("是否是超级管理员")
    private Boolean isSuperAdmin;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;

    @TableField(exist = false)
    private String roleId;
}
