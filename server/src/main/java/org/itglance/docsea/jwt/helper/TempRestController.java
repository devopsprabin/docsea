package org.itglance.docsea.jwt.helper;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api")
public class TempRestController {


    @GetMapping("/secured/user")
    public ResponseEntity<String> responseUser(){
        return new ResponseEntity<>(" Response for normal user", HttpStatus.OK);

    }

    @GetMapping("/test/secured/user")
    public ResponseEntity<String> test() {
        return new ResponseEntity<>(" Response api/test/secured/user", HttpStatus.OK);
    }

        @PreAuthorize("hasAnyRole('0')")
    @GetMapping("/secured/admin")
    public ResponseEntity<String> responseAdmin(){
        return new ResponseEntity<>(" Response for admin", HttpStatus.OK);

    }
    @GetMapping("/any")
    public ResponseEntity<String> responseAny() {
        return new ResponseEntity<>(" Response for any user", HttpStatus.OK);

    }

}
