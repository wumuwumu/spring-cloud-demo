package cn.overhill.user.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "hello world 接口")
@RestController()
@RequestMapping(value = "/user")
public class HelloController {

    @ApiOperation(value = "say hello word")
    @GetMapping(value = "hello")
    public String say(){
        return "hello world";
    }
}
