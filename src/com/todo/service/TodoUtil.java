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
	
	public static void createItem(TodoList list) {
		
		String title, desc, category, due_date;
		Scanner sc = new Scanner(System.in);
		
		System.out.print("\n"
				+ "========== [�׸� �߰��ϱ�] ==========" + "\n" +"������ �Է��� �ּ��� > ");
		
		title = sc.next();
		if (list.isDuplicate(title)) {
			System.out.printf("������ �ߺ��˴ϴ�");
			return;
		}
		
		System.out.print("ī�װ��� �Է��� �ּ��� > ");
		category = sc.next();
		
		sc.nextLine();
		System.out.print("������ �Է��� �ּ��� > ");
		desc = sc.nextLine().trim();
		
		
		System.out.print("�������ڸ� �Է��� �ּ��� (????/??/??) >");
		due_date = sc.nextLine().trim();
		
		TodoItem t = new TodoItem(title, desc,category,due_date);
		list.addItem(t);
		System.out.println("�׸� �߰��Ǿ����ϴ�.");
		System.out.println("===============================");
	}

	public static void deleteItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("\n" + "========== [�׸� �����ϱ�] ==========" + "\n" +"������ ��ȣ�� �Է��� �ּ��� > ");
		int choice = sc.nextInt();
		int i = 0;
		//String title = sc.next();
		
		for (TodoItem item : l.getList()) {
			if (i == (choice - 1)) {
				l.deleteItem(item);
				System.out.println("�׸��� ���� �Ǿ����ϴ�");
				System.out.println("===============================");
				break;
			}
			i++;
		}
	}


	public static void updateItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("\n" + "========== [�׸� �����ϱ�] ==========" + "\n" +"������ ��ȣ�� �Է��� �ּ��� > ");
		int choice = sc.nextInt();
		int i = 0;
		
		if (l.getSize() < choice) {
			System.out.printf("��ȣ�� �������� �ʽ��ϴ�");
			System.out.println("===============================");
			return;
		}	
		
		System.out.print("�� ������ �Է��� �ּ��� > ");
		String new_title = sc.next().trim();
		if (l.isDuplicate(new_title)) {
			System.out.printf("������ �ߺ��˴ϴ�");
			return;
		}
		
		System.out.print("�� ī�װ��� �Է��� �ּ��� > ");
		String new_category = sc.next();
		
		sc.nextLine();
		System.out.print("�� ������ �Է��� �ּ��� > ");
		String new_description = sc.nextLine().trim();
		
		
		System.out.print("�� �������� �Է��� �ּ��� (????/??/??) > ");
		String new_due_date = sc.nextLine().trim();
		
		for (TodoItem item : l.getList()) {
			if (i == (choice - 1)) {
				l.deleteItem(item);
				TodoItem t = new TodoItem(new_title, new_description, new_category, new_due_date);
				l.addItem(t);
				System.out.println("�׸��� ���� �Ǿ����ϴ�");
				System.out.println("===============================");
				break;
			}
			i++;
		}		
	}

	public static void listAll(TodoList l) {
		System.out.println("========== [��ü ���] ==========" + "\n");
		System.out.printf("[��ü ���, �� %d��]\n", l.getSize());
		int i = 1;
		for (TodoItem item : l.getList()) {
			System.out.println(i + ". [ī�װ�] " + item.getCategory()+ " | [����] " + item.getTitle() + " | [����] " + item.getDesc() + " | [��¥] " + item.getCurrent_date() + " | [������] " + item.getDue_date());
			i++;
		}
	}

	public static void Loadlist(TodoList l, String filename) {
		// BufferedReader, FileReader, StringTokenizer
		try
		{
			BufferedReader br = new BufferedReader(new FileReader(filename));
			
			String line;
			
			while((line = br.readLine()) != null)
			{
				
				StringTokenizer st = new StringTokenizer(line, "##");
				
				String category = st.nextToken();
				String title = st.nextToken();
				String desc = st.nextToken();
				String current_date = st.nextToken();
				String due_date = st.nextToken();
				
				TodoItem t = new TodoItem(title, desc, current_date, category, due_date);
				l.addItem(t);
				
			}
			System.out.println("���� �ҷ����� ����");
		}	
		catch(FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}

	public static void saveList(TodoList l, String filename) {
		// FileWriter
		try
		{
			Writer w = new FileWriter(filename);
			
			for (TodoItem myitem : l.getList()) {
				w.write(myitem.toSaveString());
			}
			w.close();
			System.out.println("���Ͽ� ����Ǿ����ϴ�");
		}
		catch(FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	
	public static void find(TodoList l, String keyword)
	{
		for (TodoItem item : l.getList())
		{
			
			if (item.getCategory().contains(keyword) || item.getCategory().contains(keyword) || item.getTitle().contains(keyword) || item.getDesc().contains(keyword) || item.getCurrent_date().contains(keyword) || item.getDue_date().contains(keyword))
			{
				System.out.println(l.indexOf(item)+1 + ". [ī�װ�] " + item.getCategory()+ " | [����] " + item.getTitle() + " | [����] " + item.getDesc() + " | [��¥] " + item.getCurrent_date() + " | [������] " + item.getDue_date());
			}
		}
	}
	public static void find_cate(TodoList l, String keyword)
	{
		for (TodoItem item : l.getList())
		{
			
			if (item.getCategory().contains(keyword))
			{
				System.out.println(l.indexOf(item)+1 + ". [ī�װ�] " + item.getCategory()+ " | [����] " + item.getTitle() + " | [����] " + item.getDesc() + " | [��¥] " + item.getCurrent_date() + " | [������] " + item.getDue_date());
			}
		}
	}
	public static void list_cate(TodoList l) {
		HashSet<String> cate = new HashSet<String>();
		for (TodoItem item : l.getList()) {
			cate.add(item.getCategory());
		}
		for(Object obj : cate.toArray()) {
			String category = (String) obj;
			System.out.print(category+" | ");
		}
		System.out.println("\n�� "+cate.size()+"���� ī�װ��� ��ϵǾ� �ֽ��ϴ�.");
	}
}
