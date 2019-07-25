package com.xuge.springws.controller;

import com.xuge.springws.services.WebSocketServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

/**
 * @Author: jack_huang
 * @Date: 2019/7/25 15:27
 */
@Controller
@RequestMapping("/checkcenter")
public class CheckCenterController {

    @Autowired
    private WebSocketServer webSocketServer;
    //页面请求
    @GetMapping("/socket/{cid}")
    public ModelAndView socket(@PathVariable String cid) {
        ModelAndView mav=new ModelAndView("/socket");
        mav.addObject("cid", cid);
        return mav;
    }
    //推送数据接口
    @ResponseBody
    @RequestMapping("/socket/push/{cid}")
    public void pushToWeb(@PathVariable String cid,String message) {
        try {
            webSocketServer.sendInfo(message);
//            webSocketServer.sendInfo(message,cid);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
