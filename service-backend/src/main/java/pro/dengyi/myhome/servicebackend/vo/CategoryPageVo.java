package pro.dengyi.myhome.servicebackend.vo;

import lombok.Data;

/**
 * @author DengYi
 * @version v1.0
 */
@Data
public class CategoryPageVo {
    private Integer pageNumber;
    private Integer pageSize;
    private String name;
}
