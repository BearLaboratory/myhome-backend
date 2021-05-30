package pro.dengyi.myhome.myhomemodel.business.backend;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @author DengYi
 * @version v1.0
 */
@Data
@ApiModel("系统权限实体")
@TableName(value = "sys_permission")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Permission {


    private String id;

    @ApiModelProperty("父ID")
    private String parentId;

    @ApiModelProperty("权限类型1--菜单,2--按钮,3--api")
    private Integer type;

    @ApiModelProperty("权限名称")
    private String permissionName;

    @ApiModelProperty("路由名称")
    private String routerName;

    @ApiModelProperty("路由路径")
    private String routerPath;

    @ApiModelProperty("路由路径")
    private String routerIcon;

    @ApiModelProperty("创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    @ApiModelProperty("更新时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;

    @ApiModelProperty("顺序，从上到下的菜单顺序就是从小到大的顺序")
    private Integer orderNo;
}
