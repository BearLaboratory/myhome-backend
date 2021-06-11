package pro.dengyi.myhome.servicefrontend.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pro.dengyi.myhome.common.response.BaseResponse;
import pro.dengyi.myhome.common.response.ResponseEnum;
import pro.dengyi.myhome.servicefrontend.service.MessageLogService;
import pro.dengyi.myhome.servicefrontend.vo.FamilyAddUserVo;

/**
 * @author BLab
 */
@Api(tags = "消息接口")
@RestController
@RequestMapping("/message")
public class MessageController {
    @Autowired
    private MessageLogService messageLogService;

    @ApiOperation("家庭添加用户")
    @PostMapping("/sendFamilyAddUser")
    public BaseResponse sendFamilyAddUser(@RequestBody @Validated FamilyAddUserVo vo) {
        //messageLogService.sendFamilyAddUser(vo);
        return new BaseResponse(ResponseEnum.SUCCESS);
    }

//    @ApiOperation("查询用户的所有信息")
//    @GetMapping("/getAllMessage")
//    public DataResponse<List<UserMessageDto>> getAllMessage() {
//        List<UserMessageDto> messageDtoList = messageLogService.getAllMessage();
//        return new DataResponse<>(ResponseEnum.SUCCESS, messageDtoList);
//    }

    @ApiOperation("读取消息")
    @GetMapping("/readMessage")
    public BaseResponse readMessage(String messageId) {
        // messageLogService.readMessage(messageId);
        return new BaseResponse(ResponseEnum.SUCCESS);
    }


}
