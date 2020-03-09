package com.arun;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;


public class studentDB {

	private DataSource datasource;
	
	public studentDB(DataSource thedatasource)
	{
		datasource=thedatasource;
	}
	
	public List<student> getStudents() throws Exception {
		List<student> students =new ArrayList<>();
		Connection con=null;
		Statement stmt=null;
		ResultSet rs=null;
		try {
		con=datasource.getConnection();
		String sql="select * from user_data order by Last_Name";
		stmt=con.createStatement();
	
		rs=stmt.executeQuery(sql);
	    
		while(rs.next())
		{
			int id=rs.getInt("ID");
			String fname=rs.getString("First_Name");
			String lname=rs.getString("Last_Name");
			String email=rs.getString("Email");
			
			student tempStudent= new student(id,fname,lname,email);
			students.add(tempStudent);
		}
		
		return students;
		
		}	
		finally
		{
			close(con,stmt,rs);
		}
	}

	private void close(Connection con, Statement stmt, ResultSet rs) {
		
		try {
			if(rs!=null)
				rs.close();
			if(stmt!=null)
				stmt.close();
			if(con!=null)
				con.close();
		}catch(Exception e) {
			e.printStackTrace();
		}

	}

	public void addstudent(student thestudent) throws Exception {
		
		Connection con=null;
		PreparedStatement ps=null;
		try
		{
			con=datasource.getConnection();
			String sql="insert into user_data"
					  +"(First_Name, Last_Name, Email)"
					  +"values(?,?,?)";
			ps=con.prepareStatement(sql);
			ps.setString(1, thestudent.getFname());
			ps.setString(2, thestudent.getLname());
			ps.setString(3, thestudent.getEmail());
			ps.execute();
		}
		finally
		{
			close(con,ps,null);
		}
	}


	public void updatestudent(student thestudent) throws Exception {
		
		Connection con=null;
		PreparedStatement ps=null;
		try
		{
			con=datasource.getConnection();
			String sql ="update user_data set First_Name=? ,Last_Name=?,Email=? where ID=?";
			ps=con.prepareStatement(sql);
			ps.setString(1, thestudent.getFname());
			ps.setString(2, thestudent.getLname());
			ps.setString(3, thestudent.getEmail());
			ps.setInt(4, thestudent.getId());
			ps.execute();
			
		}
		finally {
			close(con, ps, null);
		}
	}

	

	public student getStud(String id) throws Exception {
		student thestudent =null;
		
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		
		try {
		
			int	studentID = Integer.parseInt(id);
			
			con=datasource.getConnection();
			String sql ="select * from user_data where ID=?";
			ps=con.prepareStatement(sql);
			ps.setInt(1, studentID);
			rs=ps.executeQuery();
			if(rs.next())
			{   
				int sid=rs.getInt("ID");
				String fname=rs.getString("First_Name");
				String lname=rs.getString("Last_Name");
				String email=rs.getString("Email");
				
				thestudent = new student(sid,fname,lname,email);
				
			}
			else
			{
				throw new Exception("Student ID Not Found : "+studentID);
			}
			
		return thestudent;
		}
		finally
		{
			close(con, ps, rs);
		}

	}

	public void deletestudent(String id) throws Exception {
		
		int	ID = Integer.parseInt(id);
		
		Connection con=null;
		PreparedStatement ps=null;
		try
		{
			con=datasource.getConnection();
			String sql ="delete from user_data where ID=?";
			ps=con.prepareStatement(sql);
			ps.setInt(1, ID);
			ps.execute();
			
		}
		finally {
			close(con, ps, null);
		}

		
	}
}