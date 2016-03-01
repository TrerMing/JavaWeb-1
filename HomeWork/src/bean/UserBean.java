package bean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dao.DBConnection;
import dao.DBOperation;

public class UserBean {
	
	private String name;
	private String password;
	private String sex;
	private String address;
	private String age;
	private String phone;
	private Integer id;
	
	
	
	
	public String getName() {
		return name;
	}




	public void setName(String name) {
		this.name = name;
	}




	public String getPassword() {
		return password;
	}




	public void setPassword(String password) {
		this.password = password;
	}




	public String getSex() {
		return sex;
	}




	public void setSex(String sex) {
		this.sex = sex;
	}




	public String getAddress() {
		return address;
	}




	public void setAddress(String address) {
		this.address = address;
	}




	public String getAge() {
		return age;
	}




	public void setAge(String age) {
		this.age = age;
	}




	public String getPhone() {
		return phone;
	}




	public void setPhone(String phone) {
		this.phone = phone;
	}




	public Integer getId() {
		return id;
	}




	public void setId(Integer id) {
		this.id = id;
	}




	//获得登入用户信息
	public ArrayList getLoginUser(String sql){
		DBOperation op = new DBOperation();
		ArrayList al = op.queryReturnList(sql);
		if(al.size() > 0)
			return (ArrayList) al.get(0);
		return null;
	}
	
	
	
	
	//更新添加删除用户信息
	public boolean updateUser(String sql, ArrayList param){
		DBOperation op = new DBOperation();
		return op.insertDeleteUpdate(sql,param);
	}
	
	
	
	   
//	public boolean getUserByName(String name, String pwd){
//		boolean flag = true;
//		UserBean us = null;
//		Connection conn = DBConnection.getConnection();
//		//SQLÓï¾ä
//		String sql = "select * from users where name=? and pwd=?";
//		try {
//			//Ô¤±àÒë
//			PreparedStatement ptmt = conn.prepareStatement(sql);
//			//Í¨¹ýname´«µÝ²ÎÊý
//			ptmt.setString(1, name);
//			ptmt.setString(2, pwd);
//			//µ÷ÓÃexecuteQuery·½·¨²éÑ¯,µÃµ½
//			ResultSet res = ptmt.executeQuery();
//			while(res.next()){
//				us = new UserBean();
//				us.setName(res.getString("name"));
//				us.setSex(res.getString("sex"));
//				us.setPhone(res.getString("phone"));
//				us.setPassword(res.getString("password"));
//			}
//			if(res.next())
//				return flag;
//			else
//				flag = false;
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			return false;
//		}
//		return flag;
//	}
	
}
