package com.zsl.shareqrcodeimage.controller;

import com.zsl.shareqrcodeimage.common.CommonResult;
import com.zsl.shareqrcodeimage.dto.ShareDto;
import com.zsl.shareqrcodeimage.dto.TorchDto;
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

    @PostMapping("detail")
    private CommonResult shareGoodsDetail(@RequestBody ShareDto shareDto){
        String result = shareService.detailShareImage(shareDto.getPrice(),shareDto.getTitle(),shareDto.getBackUrl(),shareDto.getContent(),shareDto.getShareId()+".png",shareDto.getWidth(),shareDto.getHeight(),shareDto.getMarginRight(),shareDto.getMarginBottom());
        return CommonResult.success(result,"海报生成成功");
    }

    @PostMapping("merge")
    private CommonResult mergeImageQrcode(@RequestBody ShareDto shareDto){
        String result = shareService.mergeImageQrcode(shareDto.getBackUrl(),shareDto.getContent(),shareDto.getShareId()+".png",shareDto.getWidth(),shareDto.getHeight(),shareDto.getMarginRight(),shareDto.getMarginBottom());
        return CommonResult.success(result,"海报生成成功");
    }

    @PostMapping("tree")
    private CommonResult mergeImageTree(@RequestBody ShareDto shareDto){
        String result = shareService.mergeImageTree(shareDto.getPrice(),shareDto.getTitle(),shareDto.getBackUrl(),shareDto.getContent(),shareDto.getShareId()+".png");
        return CommonResult.success(result,"海报生成成功");
    }

    @PostMapping("torch")
    private CommonResult mergeImageTorch(@RequestBody TorchDto torchDto){
        String result = null;
        try {
            result = shareService.mergeImageTorch(torchDto.getNickName(),torchDto.getHeadImg(),torchDto.getPreferCount(),torchDto.getTitle1(),torchDto.getTitle2(),torchDto.getProductName(),torchDto.getProductDesc(),torchDto.getShareId()+".png");
        } catch (Exception e) {
            e.printStackTrace();
            return CommonResult.success(result,"火炬海报生成失败");
        }
        return CommonResult.success(result,"火炬海报生成成功");
    }
}
