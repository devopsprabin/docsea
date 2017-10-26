package org.itglance.docsea.web.rest;

import org.itglance.docsea.service.SessionService;
import org.itglance.docsea.service.UserService;
import org.itglance.docsea.service.dto.SessionDTO;
import org.itglance.docsea.service.dto.UserDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by soni on 5/8/2017.
 */

@CrossOrigin
@RestController
@RequestMapping(value = "/api")
public class UserController {
    @Autowired
    SessionService sessionService;

    @Autowired
    UserService userService;

    public static final Logger logger = LoggerFactory.getLogger(HospitalController.class);

    @PostMapping(value = "/login")

    public ResponseEntity<?> login(@RequestBody UserDTO userDTO){
        System.out.println("******************* LOGIN ********************");
        System.out.println(userDTO.toString());
        logger.info("Validating username and password");
        SessionDTO sessionDTO = userService.validateLogin(userDTO);
        if(sessionDTO == null){
            logger.error("Invalid Username or Password.");
            System.out.println("Invalid Username or Password.");
            return new ResponseEntity<String>("Invalid Username or Password.", HttpStatus.CONFLICT);
        }
        if(!userService.isUserActive(sessionDTO.getUserId())){
            logger.error("Your Hospital registration is on the way for verification.......");
            System.out.println("Your Hospital registration is on the way for verification.......");
            return new ResponseEntity<String>("Your Hospital registration is on the way for verification.......", HttpStatus.CONFLICT);
        }
        return  new ResponseEntity<SessionDTO>(sessionDTO, HttpStatus.OK);
    }

    @PostMapping(value = "/logout")
    public ResponseEntity logout(@RequestBody String token){
        System.out.println(token);
        sessionService.removeSession(token);
        return new ResponseEntity<String>(token, HttpStatus.OK);
    }

}
