package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class DBOperation {
	//���ݿ����Ӷ���
	private Connection conn = null;
	private Statement st = null;
	private PreparedStatement pst = null;
	private ResultSet rs = null;
	
	//����ת����
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
	
	//����ɾ���޸�
	
	public boolean insertDeleteUpdate(String sql, ArrayList param){
		boolean flag = true;
		conn = DBConnection.getConnection();
		if(conn == null){
			System.out.println("���ݿ�û������");
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
	
	//�������ݵ��޸Ĳ���
	public boolean UpdateByBatch(String sql, ArrayList param){
		boolean flag = false;
		conn = DBConnection.getConnection();
		try {
			pst = conn.prepareStatement(sql);
			conn.setAutoCommit(false);
			//���������쳣�����ִ�е�try����꣬������ת��catch���
			for(int i = 0; i < param.size(); i++){
				DataChange((ArrayList)param.get(i), pst);
				pst.addBatch();
			}
			pst.executeBatch();
			//commit:���ɹ�ִ�������в������������������
			conn.commit();
			flag = true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			//�������쳣��������ݿ�����������ɵĲ���ȫ������
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
	
	//���ؽ�����ͽ�����������Ĳ�ѯ��������ͨ��ѯ
	
	public ArrayList queryReturnList(String sql){
		ArrayList al = new ArrayList();
		ResultSetMetaData rsmd = null;
		String colname[];
		int columns;
		conn = DBConnection.getConnection();
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);//�õ���ѯ���һ�����ݼ�
			if(rs == null)
				al = null;
			else{
				rsmd = rs.getMetaData();//�õ�������Ľṹ��Ϣ�������ֶ������ֶ����ȡ�
				columns = rsmd.getColumnCount();//�õ����ݼ�������
				colname = new String[columns];
				for(int i = 1; i <= columns; i++)
					colname[i-1] = rsmd.getColumnName(i);//���ݿ��е�����
				
				ArrayList al_colname = new ArrayList();
				for(int i = 1; i <= columns; i++)
					al_colname.add(colname[i-1]);
				al.add(al_colname);//�ѽ�����и�����¼���η��뷵��list�е�������
				//al�ĵ�һ��������������
				while(rs.next()){
					ArrayList alRow = new ArrayList();
					for(int i = 1; i <= columns; i++)
						alRow.add(rs.getString(colname[i-1]));//alRow����ļ���
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
	
	
	//����Boolean�Ĳ�ѯ����������login�ȷ���
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
	
	//�������ܼ�¼��
	public int getCount(String sql){
		conn = DBConnection.getConnection();
		int num = 0;
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			rs.next();//rs�ǽ��������ѯ���ļ�¼��һ���б���ʼʱָ��ָ����ǵ�һ����¼֮ǰ�ġ�
			num = rs.getInt(1);//�õ���һ����¼�����б�����
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			DBConnection.close(conn, st, rs);
		}
		return num;
	
	}
	
	

}
