package pro.dengyi.myhome.servicebackend.vo;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 登录数据封装实体
 *
 * @author DengYi
 * @version v1.0
 */
@Data
public class HospitalLoginVo {

    @NotBlank
    private String name;

    @NotBlank
    private String password;
}
