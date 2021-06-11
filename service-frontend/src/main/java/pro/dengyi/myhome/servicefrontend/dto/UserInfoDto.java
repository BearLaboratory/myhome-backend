package pro.dengyi.myhome.servicefrontend.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import pro.dengyi.myhome.myhomemodel.business.frontend.Family;
import pro.dengyi.myhome.myhomemodel.business.frontend.User;


/**
 * @author BLab
 */
@Data
@ApiModel("用户消息实体")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserInfoDto {

    @ApiModelProperty("用户信息")
    private User user;

    @ApiModelProperty("用户所选择家庭信息")
    private Family family;

    private Boolean isHolder;

}
