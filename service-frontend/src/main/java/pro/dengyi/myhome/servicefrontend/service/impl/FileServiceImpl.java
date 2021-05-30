package pro.dengyi.myhome.servicefrontend.service.impl;


import com.aliyun.oss.OSSClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pro.dengyi.myhome.servicefrontend.peoperties.OssProperties;
import pro.dengyi.myhome.servicefrontend.service.FileService;

import java.io.IOException;
import java.net.URL;
import java.security.SecureRandom;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

/**
 * @author BLab
 */
@Service
public class FileServiceImpl implements FileService {
    @Autowired
    private OssProperties ossProperties;

    @Override
    public String uploadImg(MultipartFile file) {
        OSSClient ossClient = new OSSClient(ossProperties.getEndpoint(), ossProperties.getAccessKeyId(), ossProperties.getAccessKeySecret());
        String fileName = file.getOriginalFilename();
        // 获取文件的后缀名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        // 生成上传文件名
        String finalFileName = System.currentTimeMillis() + "" + new SecureRandom().nextInt(0x0400) + suffixName;
        String objectName = "dengyi/" + finalFileName;
        try {
            ossClient.putObject("dengyi", objectName, file.getInputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        //设置过期时间
        LocalDate of = LocalDate.of(2999, 1, 1);
        ZonedDateTime zonedDateTime = of.atStartOfDay(ZoneId.systemDefault());
        Date expiration = Date.from(zonedDateTime.toInstant());
        // 生成以GET方法访问的签名URL，访客可以直接通过浏览器访问相关内容。
        URL url = ossClient.generatePresignedUrl("dengyi", objectName, expiration);
        ossClient.shutdown();
        return "https://image.dengyi.pro/" + objectName;
    }
}
