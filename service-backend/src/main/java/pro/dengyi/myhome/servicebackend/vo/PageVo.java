package pro.dengyi.myhome.servicebackend.vo;

import lombok.Data;

/**
 * @author DengYi
 * @version v1.0
 */
@Data
public class PageVo {
    private Integer pageNumber;
    private Integer pageSize;
    private String phone;
    private String name;
}
