package pro.dengyi.myhome.servicefrontend.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * 手机号登录vo
 *
 * @author DengYi
 * @version v1.0
 */
@Data
@ApiModel("手机号登录实体")
public class PhoneLoginVo {

    @ApiModelProperty(value = "手机号")
    @NotBlank
    private String phone;

    @ApiModelProperty(value = "六位验证码")
    @NotBlank
    private String validCode;
}
