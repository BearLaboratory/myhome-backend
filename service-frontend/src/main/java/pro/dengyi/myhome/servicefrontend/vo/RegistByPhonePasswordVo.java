package pro.dengyi.myhome.servicefrontend.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * @author BLab
 */
@Data
public class RegistByPhonePasswordVo {

    @NotBlank
    @Length(min = 11, max = 11)
    @ApiModelProperty(value = "11为手机号", required = true)
    @Pattern(regexp = "")
    private String phone;

    @NotBlank
    @Length(min = 6, max = 11)
    @ApiModelProperty(value = "密码", required = true)
    private String password;

}
