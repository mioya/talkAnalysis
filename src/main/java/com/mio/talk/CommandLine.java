package com.mio.talk;

import lombok.Data;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.FileInputStream;

@Component
@CommonsLog
public class CommandLine {

    @Bean
    public CommandLineRunner loadData(UserRepository userRepository) {
        try {
            String filePath = "/Users/mio/1.txt";
            FileInputStream fileStream;
            fileStream = new FileInputStream(filePath);
            byte[] readBuffer = new byte[fileStream.available()];
            Integer index = 0;
            while (fileStream.read(readBuffer) != -1) {
                String full = new String(readBuffer);
                for (String line : full.split("\\n")) {
                    index = index + 1;
                    if (index > 7 && line.startsWith("20")
                            && line.contains(",")) {
                        String date = line.substring(0, line.indexOf(","));
                        String nameConv = line.substring(line.indexOf(",") + 2, line.length() - 1);
                        if(nameConv.indexOf(":") != -1){
                            String name = nameConv.substring(0, nameConv.indexOf(":")-1);
                            String talk = nameConv.substring(nameConv.indexOf(":")+2);
                            userRepository.save(new User(name, date, talk));
                        }
                    }
                }
            }
            fileStream.close();
        } catch (Exception e) {
            e.getStackTrace();
        }

        return (args) -> {

        };
    }

}