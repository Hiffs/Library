package cn.dbc;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Base {
	Connection conn=null;
	PreparedStatement ptm=null;
	ResultSet rs=null;
	
	public Connection getConnection(){
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/library","root","123456");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	public void closeConnection(Connection conn,ResultSet rs,PreparedStatement ptm){
		try {
			if(rs!=null)rs.close();
			if(ptm!=null)ptm.close();
			if(conn!=null)conn.close();
		} catch (Exception e) {
		}
	}
	
	public boolean getUpdate(String sql,List<Object> list){
		try {
			ptm=getConnection().prepareStatement(sql);
			if(list.size()>0){
				for(int i=0;i<list.size();i++){
					ptm.setObject(i+1, list.get(i));
				}
			}
			int num=ptm.executeUpdate();
			if(num>0){
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally
		{
			closeConnection(conn, rs, ptm);
		}
		return false;
	}
	
	public <T>List<T> getExecute(String sql,List<Object> list,Class clazz){
		List<T> li=new ArrayList<T>();
		try {
			ptm=getConnection().prepareStatement(sql);
			if(list.size()>0){
				for(int i=0;i<list.size();i++){
					ptm.setObject(i+1, list.get(i));
				}
			}
			rs=ptm.executeQuery();
			ResultSetMetaData rsm=rs.getMetaData();
			int count=rsm.getColumnCount();
			while(rs.next()){
				Object obj=clazz.newInstance();
				for(int i=1;i<=count;i++){
					String name=rsm.getColumnName(i);
					Object o=rs.getObject(i);
					Field f=clazz.getDeclaredField(name);
					f.setAccessible(true);
					f.set(obj, o);
				}
				li.add((T)obj);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally
		{
			closeConnection(conn, rs, ptm);
		}
		return li;
	}
	
}
