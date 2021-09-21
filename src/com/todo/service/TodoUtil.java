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
		
		String title, desc;
		Scanner sc = new Scanner(System.in);
		
		System.out.print("\n"
				+ "========== [항목 추가하기] ==========" + "\n" +"제목을 입력해 주세요 > ");
		
		title = sc.next();
		if (list.isDuplicate(title)) {
			System.out.printf("제목이 중복됩니다");
			return;
		}
		
		sc.nextLine();
		System.out.print("내용을 입력해 주세요 > ");
		desc = sc.nextLine().trim();
		
		TodoItem t = new TodoItem(title, desc);
		list.addItem(t);
		System.out.println("항목에 추가되었습니다.");
		System.out.println("===============================");
	}

	public static void deleteItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("\n" + "========== [항목 삭제하기] ==========" + "\n" +"삭제할 제목을 입력해 주세요 > ");
		String title = sc.next();
		
		for (TodoItem item : l.getList()) {
			if (title.equals(item.getTitle())) {
				l.deleteItem(item);
				System.out.println("항목이 삭제 되었습니다");
				System.out.println("===============================");
				break;
			}
		}
	}


	public static void updateItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("\n" + "========== [항목 수정하기] ==========" + "\n" +"수정할 제목을 입력해 주세요 > ");
		String title = sc.next().trim();
		if (!l.isDuplicate(title)) {
			System.out.printf("제목이 존재하지 않습니다");
			System.out.println("===============================");
			return;
		}

		System.out.print("새 제목을 입력해 주세요 > ");
		String new_title = sc.next().trim();
		if (l.isDuplicate(new_title)) {
			System.out.printf("제목이 중복됩니다");
			return;
		}
		sc.nextLine();
		System.out.print("새 내용을 입력해 주세요 > ");
		String new_description = sc.nextLine().trim();
		
		for (TodoItem item : l.getList()) {
			if (item.getTitle().equals(title)) {
				l.deleteItem(item);
				TodoItem t = new TodoItem(new_title, new_description);
				l.addItem(t);
				System.out.println("항목이 수정 되었습니다");
				System.out.println("===============================");
			}
		}

	}

	public static void listAll(TodoList l) {
		System.out.println("========== [전체 목록] ==========" + "\n");
		for (TodoItem item : l.getList()) {
			System.out.println("[제목] " + item.getTitle() + " | [내용] " + item.getDesc() + " | [날짜] " + item.getCurrent_date());
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
				
				String title = st.nextToken();
				String desc = st.nextToken();
				String current_date = st.nextToken();
				
				TodoItem t = new TodoItem(title, desc, current_date);
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
}
