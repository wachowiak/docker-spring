package org.wachowiak.docker;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
class MathController {

    @RequestMapping("/")
    String hello(){
        return "Hello there";
    }
}
