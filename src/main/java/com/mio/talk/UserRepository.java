package com.mio.talk;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findByName(String name);
    List<User> findAllByTimeStampBetween(LocalDateTime startDate, LocalDateTime finishDate);

    @Query(value = "SELECT u.name FROM User u group by u.name")
    List<String> findNameGroupByName();
}