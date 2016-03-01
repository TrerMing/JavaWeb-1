package test;

import javax.servlet.http.HttpSession;

import dao.DBOperation;

public class fuck {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String name = "xls";
		String pwd = "123456";
		int id = 1;
		DBOperation dbo = new DBOperation();
		String sql1 = "select * from books where id='"+id+"'";
		if(dbo.queryReturnboolean(sql1)){
			System.out.println("true");
		}
		else
			System.out.println("false");
	}

}
