package pro.dengyi.myhome.servicefrontend.vo;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author BLab
 */
@Data
public class AppOnlineVo {

    @NotBlank
    private String familyId;

    @NotBlank
    private String userId;

    @NotNull
    private Boolean status;

}
