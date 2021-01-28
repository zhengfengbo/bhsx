package com.bh.oneproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class Begin {
    @RequestMapping("/index")
    public String index(){
        return "frame";
    }
}
