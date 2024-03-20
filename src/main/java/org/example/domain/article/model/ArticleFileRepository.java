package org.example.domain.article.model;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ArticleFileRepository {
    private int latestId = 1;

    private final ObjectMapper objectMapper = new ObjectMapper();
    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");


    // 게시물 저장
    public void saveArticle(String title, String body){
        // 번호는 latestId, 제목이 title, 내용이 body, 조회수 0, 등록날짜 현재시간인 게시물을
        // json 파일로 저장하기
        Article article = new Article(latestId++, title, body, 0, LocalDateTime.now());

        // JSON 파일 경로 설정
        String fileName = "article_" + article.getId() + ".json";
        String filePath = "articles/" + fileName; // 예시로 articles 폴더 안에 저장

        try {
            // 객체를 JSON 파일로 변환하여 저장
            objectMapper.writeValue(new File(filePath), article);
            System.out.println("게시물이 성공적으로 저장되었습니다.");
        } catch (IOException e) {
            System.out.println("게시물 저장 중 오류가 발생하였습니다: " + e.getMessage());
        }
    }
}
