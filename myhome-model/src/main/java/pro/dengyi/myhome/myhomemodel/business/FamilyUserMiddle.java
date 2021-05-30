package pro.dengyi.myhome.myhomemodel.business;


import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * @author BLab
 */
@Data
@TableName("t_family_user_middle")
@ApiModel("家庭")
public class FamilyUserMiddle {
    private String id;
    private String userId;
    private String familyId;
    private Boolean isHolder;
}
