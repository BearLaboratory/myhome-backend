package pro.dengyi.myhome.myhomemodel.business.frontend;


import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author BLab
 */
@Data
@TableName("family_user_middle")
@ApiModel("家庭用户关联")
public class FamilyUserMiddle {

    @ApiModelProperty(value = "ID")
    private String id;

    @ApiModelProperty(value = "用户ID")
    private String userId;

    @ApiModelProperty(value = "家庭ID")
    private String familyId;

    @ApiModelProperty(value = "是否是户主")
    private Boolean isHolder;
}
