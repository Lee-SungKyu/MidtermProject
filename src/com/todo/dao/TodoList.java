package com.todo.dao;


import java.io.BufferedReader;
import java.io.FileReader;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.StringTokenizer;

import com.todo.service.DbConnect;

public class TodoList {
	Connection conn;
	
	public TodoList()
	{
		this.conn = DbConnect.getConnection();
	}
	
	public int addItem(TodoItem t)
	{
		String sql = "insert into list (name, priority, title, desc, category, current_date, due_date)" + " values (?,?,?,?,?,?,?);";
		
		PreparedStatement pstmt;
		int count = 0;
		
		try
		{
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,t.getName());
			pstmt.setInt(2, t.getPriority());
			pstmt.setString(3, t.getTitle());
			pstmt.setString(4, t.getDesc());
			pstmt.setString(5, t.getCategory());
			pstmt.setString(6, t.getCurrent_date());
			pstmt.setString(7, t.getDue_date());
			count = pstmt.executeUpdate();
			pstmt.close();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return count;
	}
	
	public int updateItem(TodoItem t)
	{
		String sql = "update list set name=?, priority=?, title=?, desc=? ,category=?, current_date=?, due_date=?" + " where id = ?;";
		PreparedStatement pstmt;
		int count = 0;
		
		try
		{
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,t.getName());
			pstmt.setInt(2, t.getPriority());
			pstmt.setString(3, t.getTitle());
			pstmt.setString(4, t.getDesc());
			pstmt.setString(5, t.getCategory());
			pstmt.setString(6, t.getCurrent_date());
			pstmt.setString(7, t.getDue_date());
			pstmt.setInt(8, t.getId());
			count = pstmt.executeUpdate();
			pstmt.close();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return count;
	}
	
	public int deleteItem(int index)
	{
		String sql = "delete from list where id=?;";
		PreparedStatement pstmt;
		int count = 0;
		try
		{
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, index);
			count = pstmt.executeUpdate();
			pstmt.close();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return count;
	}
	public int completeItem(int items, int num)
	{
		String sql = "update list set is_completed=?" + " where id = ?;";
		PreparedStatement pstmt;
		int count = 0;
		
		try
		{
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.setInt(2, items);
			count = pstmt.executeUpdate();
			pstmt.close();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return count;
	}
	
	public ArrayList<TodoItem> getList()
	{
		ArrayList<TodoItem> list = new ArrayList<TodoItem>();
		Statement stmt;
		
		try
		{
			stmt = conn.createStatement();
			String sql = "SELECT * FROM list";
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next())
			{
				int id = rs.getInt("id");
				String name = rs.getString("name");
				int priority = rs.getInt("priority");
				String category = rs.getString("category");
				String title = rs.getString("title");
				String description = rs.getString("desc");
				String due_date = rs.getString("due_date");
				String current_date = rs.getString("current_date");
				int is_completed = rs.getInt("is_completed");
				
				TodoItem t = new TodoItem(name,priority,title, description, category, due_date,is_completed);
				t.setId(id);
				t.setCurrent_date(current_date);
				list.add(t);
			}
			stmt.close();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return list;
	}
	public ArrayList<TodoItem> getList(int num)
	{
		ArrayList<TodoItem> list = new ArrayList<TodoItem>();
		Statement stmt;
		
		try
		{
			stmt = conn.createStatement();
			String sql = "SELECT * FROM list WHERE is_completed = " + num;
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next())
			{
				int id = rs.getInt("id");
				String name = rs.getString("name");
				int priority = rs.getInt("priority");
				String category = rs.getString("category");
				String title = rs.getString("title");
				String description = rs.getString("desc");
				String due_date = rs.getString("due_date");
				String current_date = rs.getString("current_date");
				int is_completed = rs.getInt("is_completed");
				
				TodoItem t = new TodoItem(name,priority,title, description, category, due_date,is_completed);
				t.setId(id);
				t.setCurrent_date(current_date);
				list.add(t);
			}
			stmt.close();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return list;
	}
	public int getCount()
	{
		Statement stmt;
		int count = 0;
		try
		{
			stmt = conn.createStatement();
			String sql = "SELECT count(id) FROM list;";
			ResultSet rs = stmt.executeQuery(sql);
			rs.next();
			count = rs.getInt("count(id)");
			stmt.close();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return count;
	}
	
	public ArrayList<TodoItem> getList(String keyword)
	{
		ArrayList<TodoItem> list = new ArrayList<TodoItem>();
		PreparedStatement pstmt;
		keyword = "%"+keyword+"%";
		
		try
		{
			String sql = "SELECT * FROM list WHERE title like ? OR desc like ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, keyword);
			pstmt.setString(2, keyword);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next())
			{
				int id = rs.getInt("id");
				String name = rs.getString("name");
				int priority = rs.getInt("priority");
				String category = rs.getString("category");
				String title = rs.getString("title");
				String description = rs.getString("desc");
				String due_date = rs.getString("due_date");
				String current_date = rs.getString("current_date");
				int is_completed = rs.getInt("is_completed");
				
				TodoItem t = new TodoItem(name,priority,title, description, category, due_date, is_completed);
				t.setId(id);
				t.setCurrent_date(current_date);
				list.add(t);
			}
			pstmt.close();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return list;
	}
	
	public ArrayList<String> getCategories()
	{
		ArrayList<String> list = new ArrayList<String>();
		Statement stmt;
		
		try
		{
			stmt = conn.createStatement();
			String sql = "SELECT DISTINCT category FROM list";
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				String category = rs.getString("category");
				list.add(category);
			}
			stmt.close();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return list;
	}
	
	public ArrayList<TodoItem> getListCategory(String keyword)
	{
		ArrayList<TodoItem> list = new ArrayList<TodoItem>();
		PreparedStatement pstmt;
		
		try
		{
			String sql = "SELECT * FROM list WHERE category = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, keyword);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				int priority = rs.getInt("priority");
				String category = rs.getString("category");
				String title = rs.getString("title");
				String description = rs.getString("desc");
				String due_date = rs.getString("due_date");
				String current_date = rs.getString("current_date");
				int is_completed = rs.getInt("is_completed");
				
				TodoItem t = new TodoItem(name,priority,title, description, category, due_date, is_completed);
				t.setId(id);
				t.setCurrent_date(current_date);
				list.add(t);
			}
			pstmt.close();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return list;
	}
	
	public ArrayList<TodoItem> getOrderedList(String orderby, int ordering)
	{
		ArrayList<TodoItem> list = new ArrayList<TodoItem>();
		Statement stmt;
		try
		{
			stmt = conn.createStatement();
			String sql = "SELECT * FROM list ORDER BY "+ orderby;
			
			if (ordering == 0)
			{
				sql += " desc";
			}
	
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next())
			{
				int id = rs.getInt("id");
				String name = rs.getString("name");
				int priority = rs.getInt("priority");
				String category = rs.getString("category");
				String title = rs.getString("title");
				String description = rs.getString("desc");
				String due_date = rs.getString("due_date");
				String current_date = rs.getString("current_date");
				int is_completed = rs.getInt("is_completed");
				
				TodoItem t = new TodoItem(name,priority,title, description, category, due_date, is_completed);
				t.setId(id);
				t.setCurrent_date(current_date);
				list.add(t);
			}
			stmt.close();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return list;
	}
	
	public Boolean isDuplicate(String title) {
		for (TodoItem item : getList()) {
			if (title.equals(item.getTitle())) return true;
		}
		return false;
	}
}
