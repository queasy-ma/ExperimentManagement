package my.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {

    @GetMapping("/index")
    public String index() {
        // 返回的路径是相对于 src/main/resources/static 目录的
        return "forward:/index.html";
    }
}

