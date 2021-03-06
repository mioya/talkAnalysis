package com.mio.talk;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Map;

@Getter
@Setter
public class HomeResponseVo {

    private int talkCnt;

    private int dailyAverageTalk;

    private String mostTalker;

    private int mostTalkCnt;

    private int attendUser;

    private Map<String, Long> nameTalkCount;

    private LocalDateTime timeStamp;

}