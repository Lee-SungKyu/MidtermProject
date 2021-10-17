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
		
		
		Menu.displaymenu();
		do {
			Menu.prompt();
			isList = false;
			String choice = sc.next();
			switch (choice) {
			
			case "ls":
				TodoUtil.listAll(l);
				break;
					
			case "add":
				TodoUtil.createItem(l);
				break;
				
			case "edit":
				TodoUtil.updateItem(l);
				break;
				
			case "del":
				TodoUtil.deleteItem(l);
				break;
				
			case "mdel":
				TodoUtil.deleteMItem(l);
				break;
			
			case "find": // find from title and desc
				System.out.print("찾을려고 하는 단어를 입력하세요 > ");
				String text = sc.next().trim();
				TodoUtil.findList(l,text);
				break;
				
			case "ls_cate": //print cate
				TodoUtil.listCateAll(l);
				break;
				
			case "find_cate": //print everything from user input category
				System.out.print("찾을려고 하는 카테고리를 입력하세요 > ");
				String text1 = sc.next().trim();
				TodoUtil.findCateList(l,text1);
				break;
			case "ls_name_asc":
				System.out.println("이름순으로 정렬하였습니다.");
				TodoUtil.listAll(l,"name",1);
				break;
				
			case "ls_name_desc":
				System.out.println("이름역순으로 정렬하였습니다.");
				TodoUtil.listAll(l,"name",0);
				break;
			case "ls_prio_asc":
				System.out.println("중요도순으로 정렬하였습니다.");
				TodoUtil.listAll(l,"priority",1);
				break;
			case "ls_prio_desc":
				System.out.println("중요도역순으로 정렬하였습니다.");
				TodoUtil.listAll(l,"priority",0);
				break;
				
			case "ls_title_asc":
				System.out.println("제목순으로 정렬하였습니다.");
				TodoUtil.listAll(l,"title",1);
				isList = true;
				break;
				
			case "ls_title_desc":
				System.out.println("제목역순으로 정렬하였습니다.");
				isList = true;
				TodoUtil.listAll(l,"title",0);
				break;
				
			case "ls_date":
				System.out.println("날짜순으로 정렬하였습니다.");
				TodoUtil.listAll(l,"due_date",1);
				isList = true;
				break;
				
			case "ls_date_desc" :
				System.out.println("날짜역순으로 정렬하였습니다.");
				TodoUtil.listAll(l,"due_date",0);
				isList= true;
				break;
			
			case "comp":
				TodoUtil.completeItem(l);
				break;
			
			case "del_comp":
				TodoUtil.delcompleteItem(l);
				break;
				
			case "ls_comp":
				TodoUtil.listAll(l, 1);
				break;
			case "ls_noncomp":
				TodoUtil.listAll(l,0);
				break;
				
			case "help":
				Menu.displaymenu();
				break;
				
			case "exit":
				quit = true;
				break;
			
			default:
				System.out.println("입력하신 커맨드가 존재하지 않습니다. 다시 입력해 주세요.");
				break;
			}
			
		} while (!quit);
	}
}
