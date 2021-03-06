package com.bean.springboot.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by bean on 2017/4/29.
 */
@Controller
public class IndexController {
    private static final Logger log = LoggerFactory.getLogger(IndexController.class);
    @RequestMapping("/")
    public String index(){
        return "index";
    }

    @RequestMapping("/1")
    public String index1(){
        return "index1";
    }
    @RequestMapping("/2")
    public String index2(){
        return "index2";
    }
}
