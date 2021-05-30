package pro.dengyi.myhome.myhomemodel.business;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.util.Date;

/**
 * 楼层实体
 *
 * @author BLab
 */
@Data
@TableName("t_floor")
@ApiModel("楼层")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Floor {

    private String id;
    private String name;
    private String familyId;
    private Date createTime;
    private Date updateTime;
}
