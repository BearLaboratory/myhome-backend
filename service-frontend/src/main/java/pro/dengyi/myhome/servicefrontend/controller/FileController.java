package pro.dengyi.myhome.servicefrontend.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import pro.dengyi.myhome.common.response.DataResponse;
import pro.dengyi.myhome.common.response.ResponseEnum;
import pro.dengyi.myhome.servicefrontend.service.FileService;


/**
 * @author BLab
 */
@Api(tags = "文件")
@RestController
@RequestMapping("/file")
public class FileController {
    @Autowired
    private FileService fileService;


    @ApiOperation("上传图片")
    @PostMapping("/uploadImg")
    public DataResponse<String> uploadImg(MultipartFile file) {
        String picUrl = fileService.uploadImg(file);
        return new DataResponse<>(ResponseEnum.SUCCESS, picUrl);
    }
}
