package pro.dengyi.myhome.servicefrontend.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author BLab
 */
public interface FileService {
    /**
     * 上传图片
     *
     * @param file
     * @return
     */
    String uploadImg(MultipartFile file);
}
