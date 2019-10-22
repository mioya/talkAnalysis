package com.mio.talk;

import lombok.extern.apachecommons.CommonsLog;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Component
@CommonsLog
public class CommandLine {

    @Bean
    public CommandLineRunner loadData(UserRepository userRepository) {
        try {
            //String filePath = "/Users/mio/1.txt";
            String filePath = "C:\\Users\\mio\\Documents\\test\\6.txt";
            FileInputStream fileStream;
            fileStream = new FileInputStream(filePath);
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy년 MM월 dd일 a KK:mm");

            byte[] readBuffer = new byte[fileStream.available()];
            Integer index = 0;
            while (fileStream.read(readBuffer) != -1) {
                String full = new String(readBuffer);
                for (String line : full.split("\\n")) {
                    index = index + 1;
                    if (index > 7 && line.startsWith("20")
                            && line.contains(",")) {
                        String stringDate = line.substring(0, line.indexOf(","));
                        Date date = formatter.parse(stringDate);
                        LocalDateTime localDateTime = getLocalDateTime(date);

                        String nameConv = line.substring(line.indexOf(",") + 2, line.length() - 1);
                        if (nameConv.indexOf(":") != -1) {
                            String name = nameConv.substring(0, nameConv.indexOf(":") - 1);
                            String talk = nameConv.substring(nameConv.indexOf(":") + 2);
                            userRepository.save(new User(name, localDateTime, talk));
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

    private LocalDateTime getLocalDateTime(Date date) {
        return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
    }

}