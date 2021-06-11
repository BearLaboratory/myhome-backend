package pro.dengyi.myhome.servicefrontend.service.impl;

import cn.hutool.core.util.RandomUtil;
import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.exceptions.ClientException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import pro.dengyi.myhome.common.exception.BusinessException;
import pro.dengyi.myhome.common.response.ResponseEnum;
import pro.dengyi.myhome.myhomeutil.SmsUtil;
import pro.dengyi.myhome.servicefrontend.peoperties.DayuProperties;
import pro.dengyi.myhome.servicefrontend.peoperties.GlobalProperties;
import pro.dengyi.myhome.servicefrontend.service.ValidCodeService;

import java.util.concurrent.TimeUnit;

/**
 * @author DengYi
 * @version v1.0
 */
@Service
public class ValidCodeServiceImpl implements ValidCodeService {
    @Autowired
    private StringRedisTemplate redisTemplate;
    @Autowired
    private DayuProperties dayuProperties;
    @Autowired
    private GlobalProperties globalProperties;

    /**
     * 通过手机号获取验证码
     * <p>
     * 黑名单暂时不做，将在后期加入平凡请求黑名单
     *
     * @param phoneString 手机号字符串
     */
    @Override
    public void getCodeByPhone(String phoneString) {
        //限流验证
        String sendCode = redisTemplate.opsForValue().get("validCode::" + phoneString);
        if (StringUtils.isNotBlank(sendCode)) {
            throw new BusinessException(ResponseEnum.CODE_FLOW_LIMIT);
        }
        //调用阿里云发送验证码
        JSONObject validCodeContent = new JSONObject();
        String realCode = RandomUtil.randomNumbers(4);
        validCodeContent.put("code", realCode);
        try {
            SmsUtil.sendSms(dayuProperties.getAccessKeyId(), dayuProperties.getAccessKeySecret(), phoneString, dayuProperties.getSignName(), dayuProperties.getTemplateCode(), validCodeContent.toString());
            //发送后将验证码存储在redis中
            redisTemplate.opsForValue().set("validCode::" + phoneString, realCode, globalProperties.getValidCodeExpireMinus(), TimeUnit.MINUTES);
        } catch (ClientException e) {
            throw new RuntimeException(e);
        }

    }
}
