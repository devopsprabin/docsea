package org.itglance.docsea.web.rest;

import org.itglance.docsea.domain.Users;
import org.itglance.docsea.restutil.HeaderUtil;
import org.itglance.docsea.service.UserService;
import org.itglance.docsea.service.UsersService;
import org.itglance.docsea.service.dto.UserDTO;
import org.itglance.docsea.service.dto.UsersDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "localhost:4200")
public class UsersController {

    private final Logger log = LoggerFactory.getLogger(UsersController.class);

    private final UsersService usersService;

    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }

    @PostMapping("/register")
    public ResponseEntity<Users> createUser(@RequestBody UsersDTO usersDTO) throws URISyntaxException {
        log.info("user information " +usersDTO.toString());
        Users newUser = usersService.createUser(usersDTO);
        return ResponseEntity
                .created(new URI("/api/register" + newUser.getLogin()))
                .headers(HeaderUtil.createAlert("userManagement.created", newUser.getLogin()))
                .body(newUser);
    }

    @GetMapping("/user")
    public ResponseEntity<List<Users>> findAllUser(){
        return new ResponseEntity<>(usersService.findAllUser(), HttpStatus.OK);
    }
}


