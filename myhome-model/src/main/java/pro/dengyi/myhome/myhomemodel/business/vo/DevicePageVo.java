package pro.dengyi.myhome.myhomemodel.business.vo;

import lombok.Data;

/**
 * @author DengYi
 * @version v1.0
 */
@Data
public class DevicePageVo {
    private Integer pageNumber;
    private Integer pageSize;
    private String name;
    private Boolean online;
    private Boolean publish;
    private String developerId;
}
