package com.mio.talk;

import org.junit.Test;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;

public class JavaBasicTest {

    @Test
    public void test() {
        try {
            String filePath = "/Users/mio/12.txt";
            FileInputStream fileStream;
            fileStream = new FileInputStream(filePath);
            byte[] readBuffer = new byte[fileStream.available()];
            Integer index = 0;
            while (fileStream.read(readBuffer) != -1) {
                String full = new String(readBuffer);
                for (String line : full.split("\\n")) {
                    index = index + 1;
                    if (index > 7) {
                        String date = line.substring(0, line.indexOf(","));
                      /*  Integer indexYearMonthDay = date.indexOf("오전") != -1 ?
                                date.indexOf("오전") : date.indexOf("오후");
                        String dataYearMonthDay = date.substring(0, indexYearMonthDay);
                        String dataTimeStamp = date.substring(indexYearMonthDay);

                        System.out.println("dataYearMonthDay" + index + "-->" + dataYearMonthDay);
                        System.out.println("dataTimeStamp" + index + "-->" + dataTimeStamp);*/

                        String nameConv = line.substring(line.indexOf(",") + 2, line.length() - 1);
                        String name = nameConv.substring(0, nameConv.indexOf(":"));
                        String talkLine = nameConv.substring(nameConv.indexOf(":") + 1);

                        System.out.println("date" + index + "-->" + date);
                        System.out.println("nameConv" + index + "-->" + nameConv);
                        System.out.println("name" + index + "-->" + name);
                        System.out.println("talkLine" + index + "-->" + talkLine);
                    }
                }
            }
            fileStream.close();
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

}