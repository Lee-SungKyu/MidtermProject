package com.todo.service;

import java.util.*;

import com.todo.dao.TodoItem;
import com.todo.dao.TodoList;

public class TodoUtil {
	
	public static void createItem(TodoList list) {
		
		String title, desc;
		Scanner sc = new Scanner(System.in);
		
		System.out.print("\n"
				+ "========== [�׸� �߰��ϱ�] ==========" + "\n" +"������ �Է��� �ּ��� > ");
		
		title = sc.next();
		if (list.isDuplicate(title)) {
			System.out.printf("������ �ߺ��˴ϴ�");
			return;
		}
		
		sc.nextLine();
		System.out.print("������ �Է��� �ּ��� > ");
		desc = sc.nextLine().trim();
		
		TodoItem t = new TodoItem(title, desc);
		list.addItem(t);
		System.out.println("�׸� �߰��Ǿ����ϴ�.");
		System.out.println("===============================");
	}

	public static void deleteItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);
		String title = sc.next();
		
		System.out.println("\n" + "========== [�׸� �����ϱ�] ==========" + "\n" +"������ ������ �Է��� �ּ��� > ");
		
		for (TodoItem item : l.getList()) {
			if (title.equals(item.getTitle())) {
				l.deleteItem(item);
				System.out.println("�׸��� ���� �Ǿ����ϴ�");
				System.out.println("===============================");
				break;
			}
		}
	}


	public static void updateItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("\n" + "========== [�׸� �����ϱ�] ==========" + "\n" +"������ ������ �Է��� �ּ��� > ");
		String title = sc.next().trim();
		if (!l.isDuplicate(title)) {
			System.out.printf("������ �������� �ʽ��ϴ�");
			System.out.println("===============================");
			return;
		}

		System.out.println("�� ������ �Է��� �ּ��� > ");
		String new_title = sc.next().trim();
		if (l.isDuplicate(new_title)) {
			System.out.printf("������ �ߺ��˴ϴ�");
			return;
		}
		sc.nextLine();
		System.out.println("�� ������ �Է��� �ּ��� > ");
		String new_description = sc.nextLine().trim();
		
		for (TodoItem item : l.getList()) {
			if (item.getTitle().equals(title)) {
				l.deleteItem(item);
				TodoItem t = new TodoItem(new_title, new_description);
				l.addItem(t);
				System.out.println("�׸��� ���� �Ǿ����ϴ�");
				System.out.println("===============================");
			}
		}

	}

	public static void listAll(TodoList l) {
		System.out.println("========== [��ü ���] ==========" + "\n");
		for (TodoItem item : l.getList()) {
			System.out.println("[����] " + item.getTitle() + " | [����] " + item.getDesc() + " | [��¥] " + item.getCurrent_date());
		}
	}
}
