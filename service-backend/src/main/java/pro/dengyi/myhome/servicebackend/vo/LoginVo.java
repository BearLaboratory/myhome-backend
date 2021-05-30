package pro.dengyi.myhome.servicebackend.vo;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;


/**
 * 登录数据封装实体
 *
 * @author DengYi
 * @version v1.0
 */
@Data
public class LoginVo {

    @NotBlank
    @Length(min = 11,max = 11)
    private String phone;

    @NotBlank
    @Length(min = 8,max = 10)
    private String password;
}
