package cn.overhill.gateway.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping()
public class HelloController {

    @GetMapping(value = "hello")
    public ResponseEntity<String> say() {
        return ResponseEntity.ok("hello word gateway");
    }


    @GetMapping("/fallback")
    public String fallback() {
        return "fallback";
    }

}
