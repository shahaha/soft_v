package com.skylab.soft_v.controller;

import com.skylab.soft_v.common.ResultBean;
import com.skylab.soft_v.component.ActionLog;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;

@RestController
@RequestMapping("/file")
@Slf4j
@Api(tags = "文件下载")
public class FileDownloadController {

    @Value("${filepath}")
    private String filepath;

    @GetMapping("/download")
    @RequiresPermissions("file_download")
    @ActionLog("下载一个文件")
    public ResultBean<?> downLoad(String address, HttpServletResponse response) throws UnsupportedEncodingException {
        if (address == null || "".equals(address)){
            return ResultBean.error("下载失败，文件名不能为空！");
        }
        File file = new File((filepath + address));
        String fileName = address.substring(address.lastIndexOf("-") + 1);
        if(file.exists()){
            response.setContentType("application/octet-stream");
            //response.setHeader("content-type", "application/octet-stream");
            response.setHeader("Content-Disposition", "attachment;fileName=" + URLEncoder.encode(fileName,"UTF-8"));
            byte[] buffer = new byte[1024];
            //输出流
            OutputStream os = null;
            try(FileInputStream fis= new FileInputStream(file);
                BufferedInputStream bis = new BufferedInputStream(fis);) {
                os = response.getOutputStream();
                int i = bis.read(buffer);
                while(i != -1){
                    os.write(buffer);
                    i = bis.read(buffer);
                }
                return null;
            } catch (Exception e) {
                log.info(e.getMessage());
                //e.printStackTrace();
                return ResultBean.error("下载失败，下载出错！");
            }
        }
        return ResultBean.error("下载失败，不存在该文件！");
    }
}