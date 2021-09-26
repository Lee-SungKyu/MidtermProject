package com.todo.menu;
public class Menu {

    public static void displaymenu()
    {
        System.out.println();
        System.out.println("[관리 명령어]");
        System.out.println("항목 추가하기 - [add]");
        System.out.println("항목 삭제하기 - [del]");
        System.out.println("항목 수정하기 - [edit]");
        System.out.println("항목 출력하기 - [ls]");
        System.out.println("항목 정렬하기(오름차순) - [ls_name_asc]");
        System.out.println("항목 정렬하기(내림차순) - [ls_name_desc]");
        System.out.println("항목 정렬하기(날짜순 오름차순) - [ls_date]");
        System.out.println("항목 정렬하기(날짜순 내림차순) - [ls_date_desc]");
        System.out.println("항목 검색하기 - [find]");
        System.out.println("항목 검색하기(카테고리) - [find_cate]");
        System.out.println("카테고리 출력하시 - [ls_cate]");
        System.out.println("도움말 - [help]");
        System.out.println("끝내기 - [exit]");
    }
    public static void prompt()
    {
    	System.out.print("커멘드 > ");
    }
}
