package edu.cczu.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import edu.cczu.service.UploadService;
import edu.cczu.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author gao
 * @date 2023-05-10
 */

@RestController
@Api(tags = "文件上传模块")
public class UploadController {

    @Autowired
    private UploadService uploadService;

    @SaCheckLogin
    @PostMapping("/upload")
    @ApiOperation("上传图片")
    public Result uploadAiImg(MultipartFile file) {

        return uploadService.uploadAiImg(file);
    }

}
