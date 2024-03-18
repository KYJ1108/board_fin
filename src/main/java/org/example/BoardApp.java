package org.example;

import java.util.ArrayList;
import java.util.Scanner;

public class BoardApp {
    public void run(){
        Scanner scan = new Scanner(System.in);

        ArrayList<String> titleList = new ArrayList<>();
        ArrayList<String> bodyList = new ArrayList<>();

        while (true){ // 반복 조건이 true이기 때문에 무한 반복
            System.out.print("명령어 : ");
            String cmd = scan.nextLine();

            // exit 프로그램 종료
            if (cmd.equals("exit")){ // 숫자가 아닌 경우 같다라는 표현을 할 때 == 이 아닌 .equals()를 사용해야 한다.
                System.out.println("프로그램을 종료합니다.");
                break; // 반복문 탈출
            }
            // add 게시물 등록
            else if(cmd.equals("add")){
                System.out.print("게시물 제목을 입력해주세요 : ");
                String title = scan.nextLine();
                titleList.add(title); // titleList배열에 title을 추가

                System.out.print("게시물 내용을 입력해주세요 : ");
                String body = scan.nextLine();
                bodyList.add(body);

                System.out.println("게시물이 등록되었습니다.");
            }
            // list 게시물 목록
            else if(cmd.equals("list")){
                System.out.println("=================");
                for(int i=0; i<titleList.size(); i++){
                    String title = titleList.get(i);
                    System.out.printf("제목 : %s\n",title);

                    String body = bodyList.get(i);
                    System.out.printf("내용 : %s\n", body);
                    System.out.println("=================");
                }
            }
            // 게시물 수정
            else if (cmd.equals("update")){

            }
        }
    }
}
