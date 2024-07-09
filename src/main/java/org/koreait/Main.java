package org.koreait;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("== 명언 앱 실행 ==");
        List<Motivation> motivations = new ArrayList<>();
        int lastMotivationId = 0;

        while (true) {
            System.out.print("명령어 ) ");
            String cmd = sc.nextLine().trim();

            if (cmd.length() == 0) {
                System.out.println("입력해주세요!");
                continue;
            }
            if (cmd.equals("exit")) {
                break;
            }
            if (cmd.equals("등록")) {
                int id = lastMotivationId + 1;
                System.out.print("명언 : ");
                String content = sc.nextLine();

                System.out.print("작가 : ");
                String author = sc.nextLine();

                String regDate = org.koreait.Util.getNow();
                Motivation motivation = new Motivation(id, regDate, content, author);
                motivations.add(motivation);
                System.out.println(id + "번 명언이 등록되었습니다.");
                lastMotivationId++;
            } else if (cmd.startsWith("목록")) {
                System.out.println("번호    /    작가    /    명언    ");
                System.out.println("=====================================");
                if (motivations.size() == 0) {
                    System.out.println("존재하지 않습니다.");
                } else {
                    String search = cmd.substring("목록".length()).trim();
                    List<Motivation> printMotivations = motivations;
                    if (search.length() > 0) {
                        printMotivations = new ArrayList<>();
                        for (Motivation motivation : motivations) {
                            if (motivation.getContent().contains(search)) {
                                printMotivations.add(motivation);
                            }
                        }
                        if (printMotivations.size() == 0) {
                            System.out.println("아무것도 없어");
                            continue;
                        }
                    }
                    for (int i = printMotivations.size() - 1; i >= 0; i--) {
                        Motivation motivation = motivations.get(i);
                        if (Util.getNow().split(" ")[0].equals(motivation.getRegDate().split(" ")[0])) {
                            System.out.printf("   %d     /   %s   /   %s  \n", motivation.getId(), motivation.getContent(), motivation.getAuthor());
                        } else {
                            System.out.printf("   %d     /   %s   /   %s  \n", motivation.getId(), motivation.getContent(), motivation.getAuthor());
                        }
                    }
                }
            } else if (cmd.startsWith("상세보기?id=")) {
                int id = Integer.parseInt(cmd.split("=")[1]);
                Motivation foundMotivation = null;
                for (Motivation motivation : motivations) {
                    if (motivation.getId() == id) {
                        foundMotivation = motivation;
                        break;
                    }
                }
                if (foundMotivation == null) {
                    System.out.println("해당 명언은 없습니다");
                    continue;
                }
                System.out.println("번호 : " + foundMotivation.getId());
                System.out.println("작성날짜 : " + foundMotivation.getRegDate());
                System.out.println("명언 : " + foundMotivation.getContent());
                System.out.println("작가 : " + foundMotivation.getAuthor());
            } else if (cmd.startsWith("삭제?id=")) {

                int id = Integer.parseInt(cmd.split("=")[1]);
                Motivation foundMotivation = null;
                for (Motivation motivation : motivations) {
                    if (motivation.getId() == id) {
                        foundMotivation = motivation;
                        break;
                    }
                }
                if (foundMotivation == null) {
                    System.out.println("해당 명언은 없습니다");
                    continue;
                }
                motivations.remove(foundMotivation);
                System.out.println(id + "번 명언이 삭제되었습니다");

            } else if (cmd.startsWith("수정?id=")) {
                int id = Integer.parseInt(cmd.split("=")[1]);

                Motivation foundMotivation = null;

                for (Motivation motivation : motivations) {
                    if (motivation.getId() == id) {
                        foundMotivation = motivation;
                        break;
                    }
                }
                if (foundMotivation == null) {
                    System.out.println("해당 명언은 없습니다");
                    continue;
                }
                System.out.println("명언(기존) : " + foundMotivation.getContent());
                System.out.println("작가(기존) : " + foundMotivation.getAuthor());
                System.out.print("명언 : ");
                String newTitle = sc.nextLine();
                System.out.print("작가 : ");
                String newBody = sc.nextLine();

                foundMotivation.setContent(newTitle);
                foundMotivation.setAuthor(newBody);

                System.out.println(id + "번 명언이 수정되었습니다");
            } else {
                System.out.println("사용할 수 없는 명령어입니다");
            }
        }
        System.out.println("== 프로그램 종료 ==");
        sc.close();
    }
}