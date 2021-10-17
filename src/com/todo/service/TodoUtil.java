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
		System.out.println("========== [전체 목록] ==========" + "\n");
		System.out.printf("[전체 목록, 총 %d개]\n", l.getCount());
		
		for (TodoItem item : l.getList()) {
			 System.out.println(item.toString());
		}
	}
	
	
	public static void createItem(TodoList list) {
		
		String name, title, desc, category, due_date;
		int priority;
		Scanner sc = new Scanner(System.in);
		
		System.out.print("\n"
				+ "========== [항목 추가하기] ==========" + "\n");
		
		System.out.print("이름을 입력해 주세요 > ");
		name = sc.next();
		
		System.out.print("중요도를 입력해 주세요 (숫자만 입력해 주세요) > ");
		priority = sc.nextInt();
		
		System.out.print("제목을 입력해 주세요 > ");
		title = sc.next();
		
		if (list.isDuplicate(title)) {
			System.out.printf("제목이 중복됩니다\n");
			return;
		}
		
		System.out.print("카테고리를 입력해 주세요 > ");
		category = sc.next();
		
		sc.nextLine();
		System.out.print("내용을 입력해 주세요 > ");
		desc = sc.nextLine().trim();
		
		
		System.out.print("마감일자를 입력해 주세요 (????/??/??) >");
		due_date = sc.nextLine().trim();
		
		TodoItem t = new TodoItem(name, priority, title, desc,category,due_date);
		
		if(list.addItem(t)>0)
		{
			System.out.println("항목에 추가되었습니다.");
			System.out.println("===============================");
		}
		
		
	}
	
	public static void updateItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("\n" + "========== [항목 수정하기] ==========" + "\n");
		
		System.out.print("수정할 번호를 입력해 주세요 > ");
		int choice = sc.nextInt();
		
		System.out.print("새 이름을 입력해 주세요 > ");
		String new_name = sc.next().trim();
		
		System.out.print("중요도를 입력해 주세요 (숫자만 입력해 주세요) > ");
		int new_priority = sc.nextInt();
		
		System.out.print("새 제목을 입력해 주세요 > ");
		String new_title = sc.next().trim();
		
		if (l.isDuplicate(new_title)) {
			System.out.printf("제목이 중복됩니다\n");
			return;
		}
		
		System.out.print("새 카테고리를 입력해 주세요 > ");
		String new_category = sc.next();
		
		sc.nextLine();
		System.out.print("새 내용을 입력해 주세요 > ");
		String new_description = sc.nextLine().trim();
		
		
		System.out.print("새 마감일을 입력해 주세요 (????/??/??) > ");
		String new_due_date = sc.nextLine().trim();
		
		TodoItem t = new TodoItem(new_name, new_priority, new_title, new_description, new_category, new_due_date);
		t.setId(choice);
		if (l.updateItem(t) > 0)
		{
			System.out.println("항목이 수정 되었습니다");
			System.out.println("===============================");
		}
		
	}
	
	public static void deleteItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("\n" + "========== [항목 삭제하기] ==========" + "\n" +"삭제할 번호를 입력해 주세요 > ");
		int choice = sc.nextInt();
		
		if (l.deleteItem(choice) > 0)
		{
			System.out.println("항목이 삭제 되었습니다");
			System.out.println("===============================");
		}
	}
	
	public static void deleteMItem(TodoList l)
	{
		Scanner sc = new Scanner(System.in);
		
		System.out.print("\n" + "========== [항목 삭제하기] ==========" + "\n");
		
		System.out.println("삭제할 항목 개수를 적어주세요 > ");
		int count = sc.nextInt();
		
		System.out.println("항목 완료할 번호를 적어주세요 (예: 1 2 4) > ");
		
		for (int i = 0; i < count; i++)
		{
			int items = sc.nextInt();
			
			if(l.deleteItem(items) > 0)
			{
				System.out.println(items + "번 항목이 삭제 되었습니다");
			}
			else
			{
				System.out.println(items + "번 항목은 존재하지 않습니다.");
			}
		}
		
		
		System.out.println("\n" + "항목들이 삭제 되었습니다.");
		System.out.println("===============================");
	}
	
	public static void completeItem(TodoList l)
	{
		Scanner sc = new Scanner(System.in);
		
		System.out.println("항목 완료할 개수를 적어주세요 > ");
		int count = sc.nextInt();
		
		System.out.println("항목 완료할 번호를 적어주세요 (예: 1 2 4) > ");
		
		for (int i = 0; i < count; i++)
		{
			int items = sc.nextInt();
			
			if (l.completeItem(items, 1) > 0)
			{
				System.out.println("완료: " + items);
			}
			else
			{
				System.out.println("비완료: " + items);
			}	
		}
	}
	public static void delcompleteItem(TodoList l)
	{
		Scanner sc = new Scanner(System.in);

		System.out.println("항목 완료할 개수를 적어주세요 > ");
		int count = sc.nextInt();
		
		System.out.println("항목 완료할 번호를 적어주세요 (예: 1 2 4) > ");
		
		for (int i = 0; i < count; i++)
		{
			int items = sc.nextInt();
			
			if (l.completeItem(items, 0) > 0)
			{
				System.out.println("완료: " + items);
			}
			else {
				System.out.println("비완료: " + items);
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
		System.out.printf("총 %d개의 항목을 찾았습니다.\n", count);
	}
	
	public static void listCateAll(TodoList l)
	{
		int count = 0;
		for (String item : l.getCategories())
		{
			System.out.print(item + " ");
			count++;
		}
		System.out.printf("\n총 %d개의 카테고리가 등록되어 있습니다.\n", count);
	}
	
	public static void findCateList(TodoList l, String cate)
	{
		int count = 0;
		
		for (TodoItem item : l.getListCategory(cate))
		{
			 System.out.println(item.toString());
			count++;
		}
		System.out.printf("\n총 %d개의 항목을 찾았습니다.\n",count);
	}
	
	public static void listAll(TodoList l, String orderby, int ordering)
	{
		System.out.printf("[전체 목록, 총 %d개]\n", l.getCount());
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
