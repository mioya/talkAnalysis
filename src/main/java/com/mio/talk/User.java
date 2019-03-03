package com.mio.talk;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@NoArgsConstructor
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String timeStamp;

    private String talk;

    public User(String name, String timeStamp, String talk){
        this.name = name;
        this.timeStamp = timeStamp;
        this.talk = talk;
    }
}