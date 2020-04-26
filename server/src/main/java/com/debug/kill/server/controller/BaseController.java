package com.debug.kill.server.controller;

import com.debug.kill.api.enums.StatusCode;
import com.debug.kill.api.response.BaseResponse;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/*
* 基础控制器
* */
//测试用、体验MVC开发流程
@Controller  //代表我们把这个类作为控制器使用，并把它加到Spring的IOC容器中
@RequestMapping("base")//添加一个统一的前缀
public class BaseController {
    //
    private static final Logger log = LoggerFactory.getLogger(BaseController.class);


    //页面跳转到welcome
    //@RequestMapping(value = "/welcome",method = RequestMethod.GET)改写法等于下面GetMapping
    /*ModelMap对象主要用于传递控制方法处理数据到结果页面，也就是说我们把结果页面上需要的数据放到ModelMap对象中即可，
    他的作用类似于request对象的setAttribute方法的作用，用来在一个请求过程中传递处理的数据。通过以下方法向页面传递参数： 
    addAttribute(String key,Object value); 
    在页面上可以通过el变量方式$key或者bboss的一系列数据展示标签获取并展示modelmap中的数据。 
    modelmap本身不能设置页面跳转的url地址别名或者物理跳转地址，那么我们可以通过控制器方法的返回值来设置跳转url地址别名或者物理跳转地址。
*/
    @GetMapping("/welcome")
    public String welcome(String name,ModelMap modelMap){
        if(StringUtils.isBlank(name)){
            name="这是welcome！";
        }
        modelMap.put("name",name);
    return "welcome";
    }


    //只发起请求让前端拿到后端的数据
    @RequestMapping(value = "/data",method = RequestMethod.GET)
    @ResponseBody
    public String data(String name){
        if(StringUtils.isBlank(name)){
            name = "这是welcome！";
        }
        return name;
    }


    @RequestMapping(value = "/response",method = RequestMethod.GET)
    @ResponseBody//将java转化成前端的json
    public BaseResponse response(String name){
        BaseResponse response = new BaseResponse(StatusCode.Success);
        if(StringUtils.isBlank(name)){
            name = "这是welcome！";
        }
        response.setData(name);
        return response;
    }



}
