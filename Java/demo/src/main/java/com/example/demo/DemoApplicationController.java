package com.example.demo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoApplicationController {

    @RequestMapping("/")  // ルートへこのメソッドをマップする
    public String test() {
        return "Hello World";
    }
}