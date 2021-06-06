package pro.dengyi.myhome.myhomemodel.business.frontend;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author BLab
 */
@Data
@TableName("t_user")
@ApiModel("用户")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class User {

    @ApiModelProperty(value = "用户ID")
    private String id;

    @ApiModelProperty(value = "微信openId")
    private String openId;

    @ApiModelProperty(value = "用户名")
    private String name;

    @ApiModelProperty(value = "头像")
    private String avatar;

    @ApiModelProperty(value = "手机号")
    private String phone;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "邮箱")
    private String email;

    @ApiModelProperty(value = "性别")
    private Integer sex;

    @ApiModelProperty(value = "选择的家庭ID")
    private String selectedFamilyId;

    @ApiModelProperty(value = "选择的楼层ID")
    private String selectedFloorId;
}
