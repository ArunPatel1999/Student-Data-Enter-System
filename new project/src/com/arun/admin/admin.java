package com.arun.admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

@WebServlet("/admin")
public class admin extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Resource(name = "jdbc/project")
	private DataSource datasource;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		try {
			
			String thecommand = request.getParameter("command");
			if(thecommand==null)
				thecommand="LIST";
			
			switch(thecommand)
			{
			
			case "ADD":
				addadmin(request,response);
				break;
				
			case "LOGIN":
				login(request,response);
				break;
				
			case "PASSWORD":
				 setpassword(request,response);
				break;
				
				
			default:
				request.getRequestDispatcher("login.jsp").include(request, response);		
			}
		} catch (Exception e) {
		
			throw new ServletException(e);
			
		}
	
	
	
	}
	private void setpassword(HttpServletRequest request, HttpServletResponse response) throws Exception {
		PrintWriter out=response.getWriter();
		Connection con=null;
		PreparedStatement ps=null;

		String username=(request.getParameter("username")).toLowerCase();
		String password=request.getParameter("password");

		try
		{ 
			con=datasource.getConnection();
			String sql="update admin set Password=? where username=?";
			ps=con.prepareStatement(sql);
			ps.setString(1, password);
			ps.setString(2, username);

			if(ps.execute())
			{
				out.print("<b>Somthing Wrong </b>");
				request.getRequestDispatcher("login.jsp").include(request, response);
			}
			else
			{
				out.print("<b> Password Are Reset </b>");
				request.getRequestDispatcher("login.jsp").include(request, response);
			}
			
		}
		catch(Exception e)
		{
			out.print(e);
		}
		finally
		{
			close(con,ps,null);
		}
		
		
	}
	private void login(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		PrintWriter out=response.getWriter();
		Connection con=null;
		PreparedStatement ps=null;

		String username=(request.getParameter("username")).toLowerCase();
		String password=request.getParameter("password");
		
		try
		{ 
			con=datasource.getConnection();
			String sql="Select Password  from admin where Username=?";
			ps=con.prepareStatement(sql);
			ps.setString(1, username);
			ResultSet rs=ps.executeQuery();
			if(rs.next())
			{
				String realpassword=rs.getString("Password");
				if(realpassword.equals(password))
					{request.getRequestDispatcher("studentDBcontrol").forward(request, response);}
				else
				{
					out.print("<b> Password Not Match");
					request.getRequestDispatcher("login.jsp").include(request, response);
				}
			}
			else
			{
				out.print("<b> User Not Found </b>");
				request.getRequestDispatcher("login.jsp").include(request, response);
			}
			rs.close();
		}
		catch(Exception e)
		{
			out.print(e);
		}
		finally
		{
			close(con,ps,null);
		}


		
	}

	private void addadmin(HttpServletRequest request, HttpServletResponse response) throws Exception {
		PrintWriter out=response.getWriter();
		Connection con=null;
		PreparedStatement ps=null;

		String username=(request.getParameter("username")).toLowerCase();
		String name=request.getParameter("name");
		String password=request.getParameter("password");
		int question=Integer.parseInt(request.getParameter("question"));
		String answer=(request.getParameter("answer")).toLowerCase();
		
		try
		{
			con=datasource.getConnection();
			String sql="insert into admin values(?,?,?,?,?)";
			ps=con.prepareStatement(sql);
			ps.setString(1, username);
			ps.setString(2, name);
			ps.setString(3, password);
			ps.setInt(4, question);
			ps.setString(5, answer);
			Boolean a= ps.execute()	;
			if(a)
			{
				out.print("<b> Smothing Wrong  </b>");
				request.getRequestDispatcher("NewAdmin.jsp").include(request, response);
			}
			else
			{	
				out.print("<b> New Admin Data ADD </b></br>");
				out.print("<b> Please Login </b>");
				request.getRequestDispatcher("login.jsp").include(request, response);
			
			}
		}
		catch(Exception e)
		{
			out.print(e);
		}
		finally
		{
			close(con,ps,null);
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
}
