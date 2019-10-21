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
        Map<String, List<User>> userMonthTalk = monthUsers.stream()
                .collect(Collectors.groupingBy(User::getName));

        for (String userMonth : userMonthTalk.keySet()) {
            log.debug("userMonth:" + userMonth + "||" + userMonthTalk.get(userMonth).size());
        }
        System.out.println(userMonthTalk);
        model.addAttribute("response", homeResponseVo);
        return "/index";
    }
}