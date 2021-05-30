package pro.dengyi.myhome.servicefrontend.dto;

import lombok.Data;
import pro.dengyi.myhome.myhomemodel.business.Family;


/**
 * @author BLab
 */
@Data
public class FamilyListDto extends Family {
    private Integer roomCount;
    private Integer floorCount;
    private Integer deviceCount;
}
