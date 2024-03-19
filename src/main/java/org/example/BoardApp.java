package org.example;

import java.util.ArrayList;
import java.util.Scanner;

public class BoardApp {
    public void run(){
        Scanner scan = new Scanner(System.in);

        ArrayList<String> titleList = new ArrayList<>();
        ArrayList<String> bodyList = new ArrayList<>();
        ArrayList<Integer> idList = new ArrayList<>();
        int latestArticleId  = 1; // 리스트 시작번호 1로 지정

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

                // 게시물이 생성될 때마다 번호를 생성해서 저장
                idList.add(latestArticleId);
                latestArticleId ++; // 게시물이 생성될 때마다 번호 증가

                System.out.println("게시물이 등록되었습니다.");
            }
            // list 게시물 목록
            else if(cmd.equals("list")){
                System.out.println("=================");
                for(int i=0; i<titleList.size(); i++){
                    String title = titleList.get(i);

                    int id = idList.get(i);
                    System.out.println("번호 : "+ id);

                    System.out.printf("제목 : %s\n",title);

                    String body = bodyList.get(i);
                    System.out.printf("내용 : %s\n", body);
                    System.out.println("=================");
                }
            }
            // 게시물 수정
            else if (cmd.equals("update")){
                System.out.print("수정할 게시물 번호를 입력해주세요 : ");
                int id = Integer.parseInt(scan.nextLine());

                System.out.print("새로운 제목을 입력해주세요 : ");
                String newTitle = scan.nextLine();

                System.out.print("새로운 내용을 입력해주세요 : ");
                String newBody = scan.nextLine();

                // 인덱스로 찾아서 수정
                titleList.set(id - 1, newTitle);
                bodyList.set(id - 1, newBody);

                System.out.printf("%d번 게시물이 수정되었습니다.\n", id);
            }
            else if (cmd.equals("delete")){
                System.out.print("삭제할 게시물 번호를 입력하세요 : ");
                int inputId = Integer.parseInt(scan.nextLine());

                for (int i=0; i<idList.size(); i++){
                    int targetId = idList.get(i);

                    if (targetId == inputId){// 삭제하고자 하는 id와 i번째 id가 같다면
                        idList.remove(i); // 삭제
                        titleList.remove(i);
                        bodyList.remove(i);
                    }
                }

                System.out.printf("%d 게시물이 삭제되었습니다.\n", inputId);
            }
        }
    }
}
