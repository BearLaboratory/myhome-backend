package pro.dengyi.myhome.servicefrontend.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;
import pro.dengyi.myhome.myhomemodel.business.Product;

/**
 * @author BLab
 */
@Repository
public interface ProductDao extends BaseMapper<Product> {
}
