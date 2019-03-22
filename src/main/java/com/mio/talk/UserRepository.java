package com.mio.talk;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findByName(String name);

    List<User> findAllByTimeStampBetween(LocalDateTime startDate, LocalDateTime finishDate);

    @Query(value = "SELECT u FROM User u " +
            "where u.timeStamp >= 1" +
            "and u.timeStamp <= 2" +
            " group by u.name")
    Map<String, Long> findNameGroupByName(LocalDateTime startDate, LocalDateTime finishDate);
}