package org.example;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class CommonUtil {
    private Scanner scan = new Scanner(System.in);

    public Scanner getScanner(){
        return scan;
    }

    public String getCurrentDateTime() {
        LocalDateTime now = LocalDateTime.now();

        // 날짜와 시간의 형식을 지정
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm:ss");

        // 지정한 형식으로 날짜와 시간 출력
        String formattedDate = now.format(formatter);

        return formattedDate;
    }
}
