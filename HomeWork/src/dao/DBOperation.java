package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class DBOperation {
	//数据库连接对象
	private Connection conn = null;
	private Statement st = null;
	private PreparedStatement pst = null;
	private ResultSet rs = null;
	
	//数据转换器
	public void DataChange(ArrayList param, PreparedStatement pst) throws SQLException{
		for(int i = 0, j = 1; i < param.size(); i++, j++){
			if(param.get(i).getClass().getName().equals("java.lang.Integer")){
				Integer temp = (Integer)(param.get(i));
				pst.setInt(j, temp.intValue());
			}
			else if(param.get(i).getClass().getName().equals("java.lang.Long")){
				Long temp = (Long)(param.get(i));
				pst.setLong(j, temp.longValue());
			}
			else if(param.get(i).getClass().getName().equals("java.lang.Float")){
				Float temp = (Float)(param.get(i));
				pst.setFloat(j, temp.floatValue());
			}
			else if(param.get(i).getClass().getName().equals("java.lang.Double")){
				Double temp = (Double)(param.get(i));
				pst.setDouble(j, temp.doubleValue());
			}
			else if(param.get(i).getClass().getName().equals("java.lang.String"))
				pst.setString(j, (String)(param.get(i)));
			else
				System.out.println("error");
		}
	}
	
	//插入删除修改
	
	public boolean insertDeleteUpdate(String sql, ArrayList param){
		boolean flag = true;
		conn = DBConnection.getConnection();
		if(conn == null){
			System.out.println("数据库没有连接");
			return false;}
		try {
			pst = conn.prepareStatement(sql);
			DataChange(param, pst);
			pst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			flag = false;
		} finally{
			DBConnection.close(conn, st, rs);
		}
		return flag;
	}
	
	//成批数据的修改操作
	public boolean UpdateByBatch(String sql, ArrayList param){
		boolean flag = false;
		conn = DBConnection.getConnection();
		try {
			pst = conn.prepareStatement(sql);
			conn.setAutoCommit(false);
			//若不出现异常则继续执行到try语句完，否则跳转到catch语句
			for(int i = 0; i < param.size(); i++){
				DataChange((ArrayList)param.get(i), pst);
				pst.addBatch();
			}
			pst.executeBatch();
			//commit:若成功执行完所有插入操作，则正常结束
			conn.commit();
			flag = true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			//若出现异常，则对数据库中所有已完成的操作全部撤销
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} finally{
			try {
				conn.setAutoCommit(true);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				DBConnection.close(conn, pst, rs);
			}
		}
		return flag;
	}
	
	//返回结果集和结果集中列名的查询，用于普通查询
	
	public ArrayList queryReturnList(String sql){
		ArrayList al = new ArrayList();
		ResultSetMetaData rsmd = null;
		String colname[];
		int columns;
		conn = DBConnection.getConnection();
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);//得到查询结果一个数据集
			if(rs == null)
				al = null;
			else{
				rsmd = rs.getMetaData();//得到结果集的结构信息，比如字段数、字段名等。
				columns = rsmd.getColumnCount();//得到数据集的列数
				colname = new String[columns];
				for(int i = 1; i <= columns; i++)
					colname[i-1] = rsmd.getColumnName(i);//数据库列的名字
				
				ArrayList al_colname = new ArrayList();
				for(int i = 1; i <= columns; i++)
					al_colname.add(colname[i-1]);
				al.add(al_colname);//把结果集中各条记录依次放入返回list中的其他行
				//al的第一行是列名的数组
				while(rs.next()){
					ArrayList alRow = new ArrayList();
					for(int i = 1; i <= columns; i++)
						alRow.add(rs.getString(colname[i-1]));//alRow是类的集合
					al.add(alRow);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			al = null;
		} finally{
			DBConnection.close(conn, st, rs);
		}
		return al;
	}
	
	
	//返回Boolean的查询操作，用于login等服务
	public boolean queryReturnboolean(String sql){
		boolean flag = true;
		conn = DBConnection.getConnection();
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			if(!rs.next())
				flag = false;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			flag = false;
		} finally{
			DBConnection.close(conn, st, rs);
		}
		return flag;
	}
	
	//计算表的总记录数
	public int getCount(String sql){
		conn = DBConnection.getConnection();
		int num = 0;
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			rs.next();//rs是结果集。查询出的记录是一个列表，初始时指针指向的是第一条记录之前的。
			num = rs.getInt(1);//得到第一个记录，即列表总数
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			DBConnection.close(conn, st, rs);
		}
		return num;
	
	}
	
	

}
