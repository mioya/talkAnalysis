package com.mio.talk;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private LocalDateTime timeStamp;

    @Lob
    private String talk;

    public User(String name, LocalDateTime timeStamp, String talk){
        this.name = name;
        this.timeStamp = timeStamp;
        this.talk = talk;
    }
}