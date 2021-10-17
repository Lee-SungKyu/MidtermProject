package com.todo.service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.*;

import com.todo.dao.TodoItem;
import com.todo.dao.TodoList;

public class TodoUtil {
	
	public static void listAll(TodoList l)
	{
		System.out.println("========== [��ü ���] ==========" + "\n");
		System.out.printf("[��ü ���, �� %d��]\n", l.getCount());
		
		for (TodoItem item : l.getList()) {
			 System.out.println(item.toString());
		}
	}
	
	
	public static void createItem(TodoList list) {
		
		String name, title, desc, category, due_date;
		int priority;
		Scanner sc = new Scanner(System.in);
		
		System.out.print("\n"
				+ "========== [�׸� �߰��ϱ�] ==========" + "\n");
		
		System.out.print("�̸��� �Է��� �ּ��� > ");
		name = sc.next();
		
		System.out.print("�߿䵵�� �Է��� �ּ��� (���ڸ� �Է��� �ּ���) > ");
		priority = sc.nextInt();
		
		System.out.print("������ �Է��� �ּ��� > ");
		title = sc.next();
		
		if (list.isDuplicate(title)) {
			System.out.printf("������ �ߺ��˴ϴ�\n");
			return;
		}
		
		System.out.print("ī�װ��� �Է��� �ּ��� > ");
		category = sc.next();
		
		sc.nextLine();
		System.out.print("������ �Է��� �ּ��� > ");
		desc = sc.nextLine().trim();
		
		
		System.out.print("�������ڸ� �Է��� �ּ��� (????/??/??) >");
		due_date = sc.nextLine().trim();
		
		TodoItem t = new TodoItem(name, priority, title, desc,category,due_date);
		
		if(list.addItem(t)>0)
		{
			System.out.println("�׸� �߰��Ǿ����ϴ�.");
			System.out.println("===============================");
		}
		
		
	}
	
	public static void updateItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("\n" + "========== [�׸� �����ϱ�] ==========" + "\n");
		
		System.out.print("������ ��ȣ�� �Է��� �ּ��� > ");
		int choice = sc.nextInt();
		
		System.out.print("�� �̸��� �Է��� �ּ��� > ");
		String new_name = sc.next().trim();
		
		System.out.print("�߿䵵�� �Է��� �ּ��� (���ڸ� �Է��� �ּ���) > ");
		int new_priority = sc.nextInt();
		
		System.out.print("�� ������ �Է��� �ּ��� > ");
		String new_title = sc.next().trim();
		
		if (l.isDuplicate(new_title)) {
			System.out.printf("������ �ߺ��˴ϴ�\n");
			return;
		}
		
		System.out.print("�� ī�װ��� �Է��� �ּ��� > ");
		String new_category = sc.next();
		
		sc.nextLine();
		System.out.print("�� ������ �Է��� �ּ��� > ");
		String new_description = sc.nextLine().trim();
		
		
		System.out.print("�� �������� �Է��� �ּ��� (????/??/??) > ");
		String new_due_date = sc.nextLine().trim();
		
		TodoItem t = new TodoItem(new_name, new_priority, new_title, new_description, new_category, new_due_date);
		t.setId(choice);
		if (l.updateItem(t) > 0)
		{
			System.out.println("�׸��� ���� �Ǿ����ϴ�");
			System.out.println("===============================");
		}
		
	}
	
	public static void deleteItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("\n" + "========== [�׸� �����ϱ�] ==========" + "\n" +"������ ��ȣ�� �Է��� �ּ��� > ");
		int choice = sc.nextInt();
		
		if (l.deleteItem(choice) > 0)
		{
			System.out.println("�׸��� ���� �Ǿ����ϴ�");
			System.out.println("===============================");
		}
	}
	
	public static void deleteMItem(TodoList l)
	{
		Scanner sc = new Scanner(System.in);
		
		System.out.print("\n" + "========== [�׸� �����ϱ�] ==========" + "\n");
		
		System.out.println("������ �׸� ������ �����ּ��� > ");
		int count = sc.nextInt();
		
		System.out.println("�׸� �Ϸ��� ��ȣ�� �����ּ��� (��: 1 2 4) > ");
		
		for (int i = 0; i < count; i++)
		{
			int items = sc.nextInt();
			
			if(l.deleteItem(items) > 0)
			{
				System.out.println(items + "�� �׸��� ���� �Ǿ����ϴ�");
			}
			else
			{
				System.out.println(items + "�� �׸��� �������� �ʽ��ϴ�.");
			}
		}
		
		
		System.out.println("\n" + "�׸���� ���� �Ǿ����ϴ�.");
		System.out.println("===============================");
	}
	
	public static void completeItem(TodoList l)
	{
		Scanner sc = new Scanner(System.in);
		
		System.out.println("�׸� �Ϸ��� ������ �����ּ��� > ");
		int count = sc.nextInt();
		
		System.out.println("�׸� �Ϸ��� ��ȣ�� �����ּ��� (��: 1 2 4) > ");
		
		for (int i = 0; i < count; i++)
		{
			int items = sc.nextInt();
			
			if (l.completeItem(items, 1) > 0)
			{
				System.out.println("�Ϸ�: " + items);
			}
			else
			{
				System.out.println("��Ϸ�: " + items);
			}	
		}
	}
	public static void delcompleteItem(TodoList l)
	{
		Scanner sc = new Scanner(System.in);

		System.out.println("�׸� �Ϸ��� ������ �����ּ��� > ");
		int count = sc.nextInt();
		
		System.out.println("�׸� �Ϸ��� ��ȣ�� �����ּ��� (��: 1 2 4) > ");
		
		for (int i = 0; i < count; i++)
		{
			int items = sc.nextInt();
			
			if (l.completeItem(items, 0) > 0)
			{
				System.out.println("�Ϸ�: " + items);
			}
			else {
				System.out.println("��Ϸ�: " + items);
			}	
		}
	}
	public static void findList(TodoList l, String keyword)
	{
		int count = 0;
		for (TodoItem item : l.getList(keyword))
		{
			System.out.println(item.toString());
			count++;
		}
		System.out.printf("�� %d���� �׸��� ã�ҽ��ϴ�.\n", count);
	}
	
	public static void listCateAll(TodoList l)
	{
		int count = 0;
		for (String item : l.getCategories())
		{
			System.out.print(item + " ");
			count++;
		}
		System.out.printf("\n�� %d���� ī�װ��� ��ϵǾ� �ֽ��ϴ�.\n", count);
	}
	
	public static void findCateList(TodoList l, String cate)
	{
		int count = 0;
		
		for (TodoItem item : l.getListCategory(cate))
		{
			 System.out.println(item.toString());
			count++;
		}
		System.out.printf("\n�� %d���� �׸��� ã�ҽ��ϴ�.\n",count);
	}
	
	public static void listAll(TodoList l, String orderby, int ordering)
	{
		System.out.printf("[��ü ���, �� %d��]\n", l.getCount());
		for (TodoItem item : l.getOrderedList(orderby, ordering))
		{
			 System.out.println(item.toString());
		}
	}
	public static void listAll(TodoList l, int is_completed)
	{
		for (TodoItem item : l.getList(is_completed))
		{
			System.out.println(item.toString());
		}
	}
}
