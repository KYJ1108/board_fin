package org.example;

import java.util.ArrayList;
import java.util.Scanner;

import java.time.LocalDateTime;
import  java.time.format.DateTimeFormatter;

public class BoardApp {
    ArrayList<Article> articleList = new ArrayList<>(); // 인스턴스 변수

    Scanner scan = new Scanner(System.in);
    int latestArticleId = 4; // 테스트 데이터 3개 있으므로 시작 번호를 4로 지정
    int WRONG_VALUE = -1; // 값의 의미를 부여

    public void run() {
        makeTestData();
        while (true) { // 반복 조건이 true이기 때문에 무한 반복
            System.out.print("명령어 : ");
            String cmd = scan.nextLine();

            // exit 프로그램 종료
            if (cmd.equals("exit")) { // 숫자가 아닌 경우 같다라는 표현을 할 때 == 이 아닌 .equals()를 사용해야 한다.
                System.out.println("프로그램을 종료합니다.");
                break; // 반복문 탈출
            }
            switch (cmd) {
                case "add" -> add();
                case "list" -> list();
                case "update" -> update();
                case "delete" -> delete();
                case "detail" -> detail();
                case "search" -> search();
                default -> System.out.println("올바른 명령어가 아닙니다.");
            }
        }
    }
    // 게시물 등록
    private  void add(){
        System.out.print("게시물 제목을 입력해주세요 : ");
        String title = scan.nextLine();

        System.out.print("게시물 내용을 입력해주세요 : ");
        String body = scan.nextLine();

        // 모든 매개변수를 받는 생성자 이용
        Article article = new Article(latestArticleId, title, body, 0, getCurrentDateTime());

        articleList.add(article);
        System.out.println("게시물이 등록되었습니다.");

        latestArticleId++; // 게시물이 생성될 때마다 번호 증가
    }
    // 게시물 목록
    private void list(){
        System.out.println("=================");
        printArticleList(this.articleList);
    }
    // 게시물 수정
    private void update(){
        System.out.print("수정할 게시물 번호를 입력해주세요 : ");
        int inputId = getParamAsInt(scan.nextLine(), WRONG_VALUE);
        if (inputId == WRONG_VALUE){
            return;
        }

        Article article = findArticleById(inputId);
        if (article == null) {
            System.out.println("없는 게시물입이다.");
            return;
        }

        System.out.print("새로운 제목을 입력해주세요 : ");
        String newTitle = scan.nextLine();

        System.out.print("새로운 내용을 입력해주세요 : ");
        String newBody = scan.nextLine();

        article.setTitle(newTitle);
        article.setBody(newBody);

        System.out.printf("%d번 게시물이 수정되었습니다.\n", inputId);
    }
    // 게시물 삭제
    private void delete(){
        System.out.print("삭제할 게시물 번호를 입력하세요 : ");
        int inputId = getParamAsInt(scan.nextLine(), WRONG_VALUE);
        if (inputId == WRONG_VALUE){
            return;
        }

        Article article = findArticleById(inputId);

        if (article == null) {
            System.out.println("없는 게시물입니다.");
            return;
        }

        articleList.remove(article);
        System.out.printf("%d 게시물이 삭제되었습니다.\n", inputId);
    }

    // 게시물 상세보기
    private void detail(){
        System.out.print("상세보기 할 게시물 번호를 입력해주세요 : ");
        int inputId = getParamAsInt(scan.nextLine(), WRONG_VALUE);
        if (inputId == WRONG_VALUE){
            return;
        }

        Article article = findArticleById(inputId);

        if (article == null) {
            System.out.println("없는 게시물 입니다.");
            return;
        }
        article.increaseHit();

        System.out.println("===============");
        System.out.println("번호 : " + article.getId());
        System.out.println("제목 : " + article.getTitle());
        System.out.println("내용 : " + article.getBody());
        System.out.println("등록날짜 : " + article.getRegDate());
        System.out.println("조회수 : " + article.getHit());
        System.out.println("===============");
    }

    // 게시물 검색
    private void search(){
        System.out.print("검색 키워드를 입력해주세요 : ");
        String keyword = scan.nextLine();

        ArrayList<Article> searchedList = new ArrayList<>();

        for (int i = 0; i < articleList.size(); i++) {
            Article article = articleList.get(i);

            if (article.getTitle().contains(keyword)) {
                searchedList.add(article);
            }
        }
        printArticleList(searchedList);
    }

    private void makeTestData() {
        Article a1 = new Article(1, "안녕하세요 반갑습니다. 자바 공부중이에요.", "냉무", 0, getCurrentDateTime());
        Article a2 = new Article(2, "자바 질문좀 할게요~", "냉무", 0, getCurrentDateTime());
        Article a3 = new Article(3, "정처기 따야되나요?", "냉무", 0, getCurrentDateTime());
        articleList.add(a1);
        articleList.add(a2);
        articleList.add(a3);
    }

    // 입력 : 찾고자 하는 게시물 번호
    // 출력 : 게시물 번호에 해당하는 인덱스
    public Article findArticleById(int id){
        for (int i=0; i<articleList.size(); i++){
            Article article = articleList.get(i);

            if (article.getId() == id){
                return article; // 원하는 것은 찾은 즉시 종료.
            }
        }
        return null;
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
