package com.mio.talk;

import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@CommonsLog
public class UserController {

    @Autowired
    public UserRepository userRepository;

    @GetMapping(path = "/")
    public String talk(Model model) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime date = LocalDateTime.of(now.getYear(), now.getMonth(), 1, 00, 00);

        List<User> monthUsers = userRepository.findAllByTimeStampBetween(date, now);

        HomeResponseVo homeResponseVo = new HomeResponseVo();
        homeResponseVo.setMonthTalk(monthUsers.size());

        Set<String> username = monthUsers.stream().collect(Collectors.groupingBy(User::getName)).keySet();

        Map<String, Long> nameTalkCount = new HashMap<>();

        for(String name : username){
            nameTalkCount.put(name, monthUsers.stream().filter(u -> u.getName().equals(name)).count());
        }
        //Map<String, Long> names = userRepository.findNameGroupByName(date, now);

        model.addAttribute("response", homeResponseVo);
        return "/index";
    }
}