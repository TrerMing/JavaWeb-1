package bean;

import java.util.ArrayList;

import dao.DBOperation;

public class BookBean {
	
	
	
	
	//Ìí¼ÓÉ¾³ýÊé¼®
	public boolean addDelUpdate(String sql, ArrayList param){
		DBOperation dbo = new DBOperation();
		boolean flag = dbo.insertDeleteUpdate(sql, param);
		return flag;
	}
		
	
	
	//ËÑË÷Êé¼®
	public ArrayList searchBook(String sql){
		DBOperation op = new DBOperation();
		ArrayList al = op.queryReturnList(sql);
		return al;
	}
		
}
