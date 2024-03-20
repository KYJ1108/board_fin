package org.example;

import java.util.ArrayList;

public class ArticleView {
    public void printArticleList(ArrayList<Article>targetList){
        System.out.println("===============");
        for (int i=0; i<targetList.size(); i++){
            Article article = targetList.get(i);

            System.out.println("번호 : "+article.getId());
            System.out.printf("제목 : %s\n",article.getTitle());
            System.out.println("===============");
        }
    }
}
