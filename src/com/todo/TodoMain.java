package com.todo;

import java.util.Scanner;

import com.todo.dao.TodoList;
import com.todo.menu.Menu;
import com.todo.service.TodoUtil;

public class TodoMain {
	
	public static void start() {
	
		Scanner sc = new Scanner(System.in);
		TodoList l = new TodoList();
		boolean isList = false;
		boolean quit = false;
		TodoUtil.Loadlist(l,"todolist.txt");
		Menu.displaymenu();
		do {
			Menu.prompt();
			isList = false;
			String choice = sc.next();
			switch (choice) {

			case "add":
				TodoUtil.createItem(l);
				break;
			
			case "del":
				TodoUtil.deleteItem(l);
				break;
			
			case "edit":
				TodoUtil.updateItem(l);
				break;
				
			case "ls":
				TodoUtil.listAll(l);
				break;

			case "ls_name_asc":
				l.sortByName();
				isList = true;
				break;

			case "ls_name_desc":
				l.sortByName();
				l.reverseList();
				isList = true;
				break;
				
			case "ls_date":
				l.sortByDate();
				isList = true;
				break;
				
			case "ls_date_desc" :
				l.sortByDate();
				l.reverseList();
				isList= true;
				break;
				
			case "find":
				System.out.print("ã������ �ϴ� �ܾ �Է��ϼ��� > ");
				String text = sc.next();
				TodoUtil.find(l, text);
				break;
				
			case "find_cate":
				System.out.print("ã������ �ϴ� ī�װ��� �Է��ϼ��� > ");
				String text1 = sc.next();
				TodoUtil.find_cate(l, text1);
				break;
				
			case "ls_cate":
				TodoUtil.list_cate(l);
				break;
			
			case "help":
				Menu.displaymenu();
				break;
				
			case "exit":
				quit = true;
				break;

			default:
				System.out.println("�Է��Ͻ� Ŀ�ǵ尡 �������� �ʽ��ϴ�. �ٽ� �Է��� �ּ���.");
				break;
			}
			
			if(isList) l.listAll();
		} while (!quit);
		TodoUtil.saveList(l,"todolist.txt");
	}
}
