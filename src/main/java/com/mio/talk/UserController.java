package com.mio.talk;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDateTime;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    public UserRepository userRepository;

    @GetMapping(path = "/")
    public String talk(Model model) {
        LocalDateTime date = LocalDateTime.of(2019, 2, 7, 15, 30);
        LocalDateTime date1 = LocalDateTime.now();

        List<User> users = userRepository.findAllByTimeStampBetween(date, date1);
        System.out.println(users);
        model.addAttribute("users", userRepository.findAll());
        return "/index";
    }
}