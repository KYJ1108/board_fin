package org.example;

import java.util.ArrayList;
import java.util.Scanner;

import java.time.LocalDateTime;
import  java.time.format.DateTimeFormatter;

public class BoardApp {
    ArticleRepository articleRepository = new ArticleRepository();

    Scanner scan = new Scanner(System.in);

    public void run() {
        articleRepository.makeTestData();
        while (true) { // 반복 조건이 true이기 때문에 무한 반복
            System.out.print("명령어 : ");
            String cmd = scan.nextLine();

            // exit 프로그램 종료
            if (cmd.equals("exit")) { // 숫자가 아닌 경우 같다라는 표현을 할 때 == 이 아닌 .equals()를 사용해야 한다.
                System.out.println("프로그램을 종료합니다.");
                break; // 반복문 탈출
            }
            switch (cmd) {
                case "add" -> articleRepository.add();
                case "list" -> articleRepository.list();
                case "update" -> articleRepository.update();
                case "delete" -> articleRepository.delete();
                case "detail" -> articleRepository.detail();
                case "search" -> articleRepository.search();
                default -> System.out.println("올바른 명령어가 아닙니다.");
            }
        }
    }
}
