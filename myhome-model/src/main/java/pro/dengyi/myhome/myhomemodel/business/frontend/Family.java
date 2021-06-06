package pro.dengyi.myhome.myhomemodel.business.frontend;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.util.Date;

/**
 * @author BLab
 */
@Data
@TableName("t_family")
@ApiModel("家庭")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Family {

    @ApiModelProperty(value = "家庭Id")
    private String id;

    @ApiModelProperty(value = "家庭名")
    @NotBlank
    @Length(min = 2, max = 10)
    private String name;

    @ApiModelProperty(value = "省")
    private String province;

    @ApiModelProperty(value = "市")
    private String city;

    @ApiModelProperty(value = "区")
    private String district;

    @ApiModelProperty(value = "街道")
    private String street;

    @ApiModelProperty(value = "城市编码")
    private String cityCode;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

}
