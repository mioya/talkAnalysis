package com.mio.talk;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    public UserRepository userRepository;

    @GetMapping(path = "/")
    public List<User> talk(){
        return userRepository.findAll();
    }
}