package main.java.controller;

import main.java.Security;
import main.java.UserService;
import main.java.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("account")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @CrossOrigin
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<?> createUser(@RequestBody User newUser) {
        if (userService.findByUsername(newUser.getUsername()) != null) {
            logger.error("Уже существует " + newUser.getUsername());
            return new ResponseEntity<>(
                    new RuntimeException("Пользователь " + newUser.getUsername() + " существует "),
                    HttpStatus.CONFLICT);
        }

        logger.info("user registered " + newUser.getUsername());

        return new ResponseEntity<>(userService.save(newUser), HttpStatus.CREATED);
    }

@CrossOrigin
@RequestMapping(value = "/login")
public Principal user(Principal principal) {
    logger.info("Вошел " +  principal);
    return  principal;
}

}



