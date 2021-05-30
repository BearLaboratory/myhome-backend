package pro.dengyi.myhome.servicebackend.vo;

import lombok.Data;

/**
 * @author DengYi
 * @version v1.0
 */
@Data
public class HospitalUserPageVo {
    private Integer pageNumber;
    private Integer pageSize;
    private String name;
    private String hospitalName;
    private String bdId;
}
