package com.todo.menu;
public class Menu {

    public static void displaymenu()
    {
        System.out.println();
        System.out.println("[���� ��ɾ�]");
        System.out.println("�׸� �߰��ϱ� - [add]");
        System.out.println("�׸� �����ϱ� - [del]");
        System.out.println("�׸� �����ϱ� - [edit]");
        System.out.println("�׸� ����ϱ� - [ls]");
        System.out.println("�׸� �����ϱ�(��������) - [ls_name_asc]");
        System.out.println("�׸� �����ϱ�(��������) - [ls_name_desc]");
        System.out.println("�׸� �����ϱ�(��¥��) - [ls_date]");
        System.out.println("���� - [help]");
        System.out.println("������ - [exit]");
    }
    public static void prompt()
    {
    	System.out.print("Ŀ��� > ");
    }
}
