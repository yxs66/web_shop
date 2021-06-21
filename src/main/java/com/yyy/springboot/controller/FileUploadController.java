package com.yyy.springboot.controller;

import com.yyy.springboot.entitys.Result;
import com.yyy.springboot.util.ResultUtil;
import org.apache.ibatis.javassist.ClassPath;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * @Deacription
 * @Author yxs
 * @Date 2021/4/22 2:11
 * @Version 1.0
 **/
@RestController
@RequestMapping("/fileUpload")
@RefreshScope
public class FileUploadController {

    @Value("${image_path}")
    private String filePath;

    @PostMapping("/{type}")
    /**
     * @Description
     * @Date 2021/4/22 11:10
     * @Param [file, type] type:0 上传商品图片 1 上传品牌图片
     * @return com.yyy.springboot.entitys.Result<java.lang.String>
     */
    public Result<String> uploadImage(@RequestParam("file") MultipartFile file, @PathVariable("type") String type) {
        String fileName = file.getOriginalFilename();
        String fileSuffix = fileName.substring(fileName.lastIndexOf('.')).toLowerCase();

        if (type.equals("0")) {
            fileName = "product/" + UUID.randomUUID() + fileSuffix;
        } else if (type.equals("1")) {
            fileName = "brand/" + UUID.randomUUID() + fileSuffix;
        }else{
            return ResultUtil.success("type不合法");
        }
        File dest = new File(filePath + fileName);
        if (!dest.getParentFile().exists())
            dest.getParentFile().mkdirs();
        try {
            file.transferTo(dest);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResultUtil.success(fileName);
    }

    @DeleteMapping()
    public Result<String> delImage(@RequestParam String imagePath) {
        File dest = new File(filePath + imagePath);
        String data = "删除成功";
        if (dest.exists())
            dest.delete();
        else
            data = "文件不存在";
        return ResultUtil.success(data);
    }
}
