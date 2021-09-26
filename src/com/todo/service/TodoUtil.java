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
				+ "========== [항목 추가하기] ==========" + "\n" +"제목을 입력해 주세요 > ");
		
		title = sc.next();
		if (list.isDuplicate(title)) {
			System.out.printf("제목이 중복됩니다");
			return;
		}
		
		System.out.print("카테고리를 입력해 주세요 > ");
		category = sc.next();
		
		sc.nextLine();
		System.out.print("내용을 입력해 주세요 > ");
		desc = sc.nextLine().trim();
		
		
		System.out.print("마감일자를 입력해 주세요 (????/??/??) >");
		due_date = sc.nextLine().trim();
		
		TodoItem t = new TodoItem(title, desc,category,due_date);
		list.addItem(t);
		System.out.println("항목에 추가되었습니다.");
		System.out.println("===============================");
	}

	public static void deleteItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("\n" + "========== [항목 삭제하기] ==========" + "\n" +"삭제할 번호를 입력해 주세요 > ");
		int choice = sc.nextInt();
		int i = 0;
		//String title = sc.next();
		
		for (TodoItem item : l.getList()) {
			if (i == (choice - 1)) {
				l.deleteItem(item);
				System.out.println("항목이 삭제 되었습니다");
				System.out.println("===============================");
				break;
			}
			i++;
		}
	}


	public static void updateItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("\n" + "========== [항목 수정하기] ==========" + "\n" +"수정할 번호를 입력해 주세요 > ");
		int choice = sc.nextInt();
		int i = 0;
		
		if (l.getSize() < choice) {
			System.out.printf("번호가 존재하지 않습니다");
			System.out.println("===============================");
			return;
		}	
		
		System.out.print("새 제목을 입력해 주세요 > ");
		String new_title = sc.next().trim();
		if (l.isDuplicate(new_title)) {
			System.out.printf("제목이 중복됩니다");
			return;
		}
		
		System.out.print("새 카테고리를 입력해 주세요 > ");
		String new_category = sc.next();
		
		sc.nextLine();
		System.out.print("새 내용을 입력해 주세요 > ");
		String new_description = sc.nextLine().trim();
		
		
		System.out.print("새 마감일을 입력해 주세요 (????/??/??) > ");
		String new_due_date = sc.nextLine().trim();
		
		for (TodoItem item : l.getList()) {
			if (i == (choice - 1)) {
				l.deleteItem(item);
				TodoItem t = new TodoItem(new_title, new_description, new_category, new_due_date);
				l.addItem(t);
				System.out.println("항목이 수정 되었습니다");
				System.out.println("===============================");
				break;
			}
			i++;
		}		
	}

	public static void listAll(TodoList l) {
		System.out.println("========== [전체 목록] ==========" + "\n");
		System.out.printf("[전체 목록, 총 %d개]\n", l.getSize());
		int i = 1;
		for (TodoItem item : l.getList()) {
			System.out.println(i + ". [카테고리] " + item.getCategory()+ " | [제목] " + item.getTitle() + " | [내용] " + item.getDesc() + " | [날짜] " + item.getCurrent_date() + " | [마감일] " + item.getDue_date());
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
			System.out.println("파일 불러오기 성공");
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
			System.out.println("파일에 저장되었습니다");
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
				System.out.println(l.indexOf(item)+1 + ". [카테고리] " + item.getCategory()+ " | [제목] " + item.getTitle() + " | [내용] " + item.getDesc() + " | [날짜] " + item.getCurrent_date() + " | [마감일] " + item.getDue_date());
			}
		}
	}
	public static void find_cate(TodoList l, String keyword)
	{
		for (TodoItem item : l.getList())
		{
			
			if (item.getCategory().contains(keyword))
			{
				System.out.println(l.indexOf(item)+1 + ". [카테고리] " + item.getCategory()+ " | [제목] " + item.getTitle() + " | [내용] " + item.getDesc() + " | [날짜] " + item.getCurrent_date() + " | [마감일] " + item.getDue_date());
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
		System.out.println("\n총 "+cate.size()+"개의 카테고리가 등록되어 있습니다.");
	}
}
