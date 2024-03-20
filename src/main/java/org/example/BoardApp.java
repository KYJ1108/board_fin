package org.example;

import java.util.ArrayList;
import java.util.Scanner;

import java.time.LocalDateTime;
import  java.time.format.DateTimeFormatter;

public class BoardApp {
    ArticleRepository articleRepository = new ArticleRepository();

    Scanner scan = new Scanner(System.in);
    int latestArticleId = 4; // 테스트 데이터 3개 있으므로 시작 번호를 4로 지정
    int WRONG_VALUE = -1; // 값의 의미를 부여

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
    public String getCurrentDateTime() {
        LocalDateTime now = LocalDateTime.now();

        // 날짜와 시간의 형식을 지정
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm:ss");

        // 지정한 형식으로 날짜와 시간 출력
        String formattedDate = now.format(formatter);

        return formattedDate;
    }

    public void printArticleList(ArrayList<Article> targetList){
        System.out.println("===============");
        for (int i=0; i<targetList.size(); i++){
            Article article = targetList.get(i);

            System.out.println("번호 : "+article.getId());
            System.out.printf("제목 : %s\n", article.getTitle());
            System.out.println("===============");
        }
    }
    private int getParamAsInt(String param, int defaultValue){
        try {
            return Integer.parseInt(param);
        }catch (NumberFormatException e){
            System.out.println("숫자를 입력해주세요.");
            return defaultValue;
        }
    }
}
