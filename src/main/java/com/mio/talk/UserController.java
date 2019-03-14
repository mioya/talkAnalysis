package com.mio.talk;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    public UserRepository userRepository;

    @GetMapping(path = "/")
    public String talk(Model model){
        LocalDateTime date = LocalDateTime.parse("20190211", DateTimeFormatter.ofPattern("yyyyMMdd"));
        LocalDateTime date1 = LocalDateTime.parse("20190213", DateTimeFormatter.ofPattern("yyyyMMdd"));

        List<User> users = userRepository.findAllByTimeStampBetween(date,date1);
        System.out.println(users);
        model.addAttribute("users",userRepository.findAll());
        return "/index";
    }
}