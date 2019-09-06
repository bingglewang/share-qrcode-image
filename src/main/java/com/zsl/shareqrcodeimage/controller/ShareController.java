package com.zsl.shareqrcodeimage.controller;

import com.zsl.shareqrcodeimage.common.CommonResult;
import com.zsl.shareqrcodeimage.dto.ShareDto;
import com.zsl.shareqrcodeimage.service.ShareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName ShareController
 * @Description TODO
 * @Author binggleW
 * @Date 2019-08-28 17:38
 * @Version 1.0
 **/
@RestController
@RequestMapping("/share")
public class ShareController {

    @Autowired
    private ShareService shareService;

    @PostMapping("create")
    private CommonResult createShareImage(@RequestBody ShareDto shareDto){
        String result = shareService.createShareImage(shareDto.getContent(),shareDto.getShareId()+".png",shareDto.getWidth(),shareDto.getHeight(),shareDto.getMarginRight(),shareDto.getMarginBottom());
        return CommonResult.success(result,"海报生成成功");
    }

}
