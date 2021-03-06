package com.bean.springboot.controller;

import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created by ppctest02 on 2017/4/27.
 */
@RequestMapping("mobile")
@Controller
public class UtilsController {

    private static final Logger log = LoggerFactory.getLogger(UtilsController.class);

    @Value(value = "${root-path}")
    private String ROOTPATH;


    @RequestMapping(value = "/sosOutImg/**")
    public void getImagess(HttpServletRequest request, HttpServletResponse resp) throws IOException {
        String path = (String) request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE);
        String pattern = (String) request.getAttribute(HandlerMapping.BEST_MATCHING_PATTERN_ATTRIBUTE);
        AntPathMatcher antPathMatcher = new AntPathMatcher();
        String finlpath = antPathMatcher.extractPathWithinPattern(pattern, path);
        File file = new File(FilenameUtils.concat(ROOTPATH, finlpath));
        resp.setHeader("Pragma", "no-cache");
        resp.setHeader("Cache-Control", "no-cache");
        resp.setDateHeader("Expires", 0);
        resp.setContentType("image/*");

        ServletOutputStream sos = resp.getOutputStream();
        try {
            if (!file.exists() || file.isDirectory()) {
                throw new FileNotFoundException();
            }
            org.apache.commons.io.FileUtils.copyFile(file, sos);
            sos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            resp.sendError(404, "找不到对应文件");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
