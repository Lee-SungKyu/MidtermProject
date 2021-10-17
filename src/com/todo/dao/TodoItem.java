package com.todo.dao;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TodoItem {
	private int id;
	private String name;
	private int priority;
    private String title;
    private String desc;
    private String current_date;
    private String category;
    private String due_date;
    private int is_completed;


    public TodoItem(String name, int priority, String title, String desc, String category, String due_date){
    	this.name = name;
    	this.priority = priority;
        this.title=title;
        this.desc=desc;
        SimpleDateFormat f = new SimpleDateFormat("yyyy/MM/dd kk:mm:ss");
        this.current_date = f.format(new Date());
        this.category = category;
    	this.due_date = due_date;
    }
    public TodoItem(String name, int priority, String title, String desc, String category, String due_date, int is_completed)
    {
    	this.name = name;
    	this.priority = priority;
    	this.title=title;
    	this.desc=desc;
    	this.category = category;
    	this.due_date = due_date;
    	this.is_completed = is_completed;
    }
    public TodoItem(int id, String name, int priority, String title, String desc, String category, String due_date, String current_date, int is_completed)
    {
    	this.id = id;
    	this.name = name;
    	this.priority = priority;
    	this.title=title;
    	this.desc=desc;
    	this.current_date = current_date;
    	this.category = category;
    	this.due_date = due_date;
    	this.is_completed = is_completed;
    }
    
   
    public int getId()
    {
    	return id;
    }
    public void setId(int id)
    {
    	this.id = id;
    }
    
    public String getName()
    {
    	return name;
    }
    public void setName(String name)
    {
    	this.name = name;
    }
    
    public int getPriority()
    {
    	return priority;
    }
    public void setPriority(int priority)
    {
    	this.priority = priority;
    }
    
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }
    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getCurrent_date() {
        return current_date;
    }
    public void setCurrent_date(String current_date) {
        this.current_date = current_date;
    }
    
    public String getCategory()
    {
    	return category;
    }
    public void setCategory(String category)
    {
    	this.category = category;
    }
    
    public String getDue_date()
    {
    	return due_date;
    }
    public void setDue_date(String due_date)
    {
    	this.due_date = due_date;
    }
    
    public int getIs_completed() {
		return is_completed;
	}

	public void setIs_completed(int is_completed) {
		this.is_completed = is_completed;
	}
    
    public String toString()
    {
    	if (is_completed == 1)
    	{
    		return id + ". [이름] : " + name + " | [중요도] : " + priority + " | [카테고리] : " + category + " | [제목] : " + title + " | [내용] : " + desc + " | [날짜] : " + current_date + " | [마감일] : " + due_date + " | [V] |";
        }
    	else
    	{
    		return id + ". [이름] : " + name + " | [중요도] : " + priority + " | [카테고리] : " + category + " | [제목] : " + title + " | [내용] : " + desc + " | [날짜] : " + current_date + " | [마감일] : " + due_date;
        }
    	
    }
    
}
