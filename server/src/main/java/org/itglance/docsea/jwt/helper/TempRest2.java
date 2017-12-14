package org.itglance.docsea.jwt.helper;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/secured")
public class TempRest2 {

    @GetMapping("/user")
    public String user(){
        return "/secured/user response";
    }
}
