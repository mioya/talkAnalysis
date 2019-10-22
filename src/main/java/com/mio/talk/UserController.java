package com.mio.talk;

import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
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

        List<User> userTalk = userRepository.findAll();

        HomeResponseVo homeResponseVo = new HomeResponseVo();
        homeResponseVo.setTalkCnt(userTalk.size());
        homeResponseVo.setAttendUser(userTalk.stream().collect(Collectors.groupingBy(User::getName)).size());

        Map<String, List<User>> userAllTalk = userTalk.stream()
                .collect(Collectors.groupingBy(User::getName));

        for (String userName : userAllTalk.keySet()) {
            if (homeResponseVo.getMostTalkCnt() < userAllTalk.get(userName).size()) {
                homeResponseVo.setMostTalkCnt(userAllTalk.get(userName).size());
                homeResponseVo.setMostTalker(userName);
            }
        }

        model.addAttribute("response", homeResponseVo);
        return "/index";
    }
}