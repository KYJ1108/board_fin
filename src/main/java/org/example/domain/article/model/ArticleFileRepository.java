package org.example.domain.article.model;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.base.CommonUtil;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class ArticleFileRepository {
    private int latestId = 1;
    private ArrayList<Article> articleList  = new ArrayList<>(); // 저장소

    public ArticleFileRepository(){
        this.articleList = loadPostsFromFile("article.json");

        if (articleList.size() == 0){
            latestId = 0;
            return;
        }
        int index = this.articleList.size()-1;
        Article article = articleList.get(index);
        latestId = article.getId();
    }

    private ArrayList<Article>loadPostsFromFile(String filePath){
        ObjectMapper mapper = new ObjectMapper();
        try {
            // 파일로부터 post 객체 리스트를 읽어옴.
            return mapper.readValue(new File(filePath), new TypeReference<ArrayList<Article>>() {});
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("파일을 읽는 도중 오류가 발생했습니다: " + e.getMessage());
            // 파일 읽기 실패 시 빈 리스트를 반환합니다.
            return new ArrayList<>();
        }
    }

    public void saveArticle(String title, String body) {
        // 번호는 latestId, 제목이 title, 내용이 body, 조회수 0, 등록날짜 현재시간인 게시물을
        // json 파일로 저장

        // 게시물 저장
        CommonUtil commonUtil = new CommonUtil();
        Article a1 = new Article(latestId, title, body, 0, commonUtil.getCurrentDateTime());
        articleList.add(a1);

        ObjectMapper mapper = new ObjectMapper();
        try {
            // 객체를 JSON 형태로 변환하여 파일에 저장
            mapper.writeValue(new File("article.json"), articleList);
            System.out.println("객체가 JSON 형태로 파일에 저장되었습니다.");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public ArrayList<Article> findAll(){
        // json 파일을 읽어와서 ArrayList로 반환
        ObjectMapper mapper = new ObjectMapper();
        ArrayList<Article> articleList = new ArrayList<>();

        try {
            articleList = mapper.readValue(new File("article.json"), new TypeReference<ArrayList<Article>>(){});
            return articleList;

        } catch (IOException e){
            e.printStackTrace();
        }
        return articleList;
    }
}
