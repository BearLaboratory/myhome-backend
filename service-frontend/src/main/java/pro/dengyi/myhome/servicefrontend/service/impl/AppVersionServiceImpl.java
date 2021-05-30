package pro.dengyi.myhome.servicefrontend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pro.dengyi.myhome.myhomemodel.business.AppVersion;
import pro.dengyi.myhome.servicefrontend.dao.AppVersionDao;
import pro.dengyi.myhome.servicefrontend.service.AppVersionService;


/**
 * @author BLab
 */
@Service
public class AppVersionServiceImpl implements AppVersionService {
    @Autowired
    private AppVersionDao appVersionDao;

    @Override
    public AppVersion getNewestApp() {
        QueryWrapper<AppVersion> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("version_code");
        queryWrapper.last("limit 1");
        return appVersionDao.selectOne(queryWrapper);
    }
}
