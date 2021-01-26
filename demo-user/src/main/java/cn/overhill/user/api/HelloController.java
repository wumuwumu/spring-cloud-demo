package cn.overhill.user.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping(value = "/user")
public class HelloController {

    @GetMapping(value = "hello")
    public String say(){
        return "hello world";
    }
}
