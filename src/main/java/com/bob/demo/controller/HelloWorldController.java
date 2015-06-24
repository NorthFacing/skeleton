package com.bob.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @ClassName: HelloWorldController 
 * @Description: freeMarker 测试范例 controller
 * @author Bob
 * @date 2015年6月24日 上午10:23:47
 */
@Controller
@RequestMapping("/freeMarker")
public class HelloWorldController {

    @RequestMapping("/hello")
    public String sayHello(ModelMap map) {
        System.out.println("say Hello ……");
        map.addAttribute("name", " World!");
        return "demo/hello.ftl";
    }

    @RequestMapping("/hi")
    public String sayHi(ModelMap map) {
        System.out.println("say hi ……");
        map.put("name", "jojo");
        return "demo/hi.ftl";
    }

    @RequestMapping("/jsp")
    public String jspRequest(ModelMap map) {
        System.out.println("jspRequest ……");
        map.put("name", "jsp");
        return "demo/temp.jsp";
    }
}
