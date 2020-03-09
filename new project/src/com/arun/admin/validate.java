package com.arun.admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;


@WebServlet("/validate")
public class validate extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Resource(name="jdbc/project")
	private DataSource datasource;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			
			String thecommand = request.getParameter("command");
			if(thecommand==null)
				thecommand="LIST";
			
			switch(thecommand)
			{
			 case "UPDATE":
				 resetpass(request,response);
				break;
				
			case "ADD":
				validatepassword(request,response);
				break;
				
			case "LOGIN":
				request.getRequestDispatcher("admin").forward(request, response);
				break;
			
			case "QUESTION":
				checkanswer(request,response);
				break;
			
			case "PASSWORD":
				newpassword(request,response);
				break;
		
			
			default:
				request.getRequestDispatcher("login.jsp").include(request, response);
				
			}
		} catch (Exception e) {
		
			throw new ServletException(e);
			
		}
	
	}


	private void newpassword(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		PrintWriter out=response.getWriter();
		
		String password=request.getParameter("password");
		String repassword=request.getParameter("repassword");
		
		if(password.length()>=8)
		{
			if(password.equals(repassword))
			{
			request.getRequestDispatcher("admin").forward(request, response);
			}
			else
			{
				out.print("please Enter Both Password Will Be Same");
				request.getRequestDispatcher("reenterpassword.jsp").include(request, response);
			}
		}
		else
		{
			out.print("<b> Please Enter 8 charcter Password </b>");
			request.getRequestDispatcher("reenterpassword.jsp").include(request, response);
		}

	}


	private void checkanswer(HttpServletRequest request, HttpServletResponse response) throws Exception{

		PrintWriter out=response.getWriter();
		Connection con=null;
		PreparedStatement ps=null;

		String username=request.getParameter("username");
		String answer=(request.getParameter("answer")).toLowerCase();
		
		try
		{ 
			con=datasource.getConnection();
			String sql="Select Answer from admin where Username=?";
			ps=con.prepareStatement(sql);
			ps.setString(1, username);
			ResultSet rs=ps.executeQuery();
			if(rs.next())
			{
				String realanswer=rs.getString("Answer");
				if(realanswer.equals(answer))
					{ request.setAttribute("username", username);
					request.getRequestDispatcher("reenterpassword.jsp").forward(request, response);}
				else
				{
					out.print("<b> Answer Not Match");
					request.getRequestDispatcher("resatpassword.jsp").include(request, response);
				}
			}
			rs.close();
		}
		catch(Exception e)
		{
			out.print(e);
		}
		finally
		{
			ps.close();

			con.close();
		}
		
	}



	private void validatepassword(HttpServletRequest request, HttpServletResponse response) throws Exception {
		PrintWriter out=response.getWriter();
		Connection con=null;
		PreparedStatement ps=null;

		String username=(request.getParameter("username")).toLowerCase();
		String password=request.getParameter("password");
		String repassword=request.getParameter("repassword");
		
		try
		{ 
			con=datasource.getConnection();
			String sql="Select Password  from admin where Username=?";
			ps=con.prepareStatement(sql);
			ps.setString(1, username);
			ResultSet rs=ps.executeQuery();
			if(rs.next() == false)
			{
				if(password.length()>=8)
				{
					
				if(password.equals(repassword))
				{

					request.getRequestDispatcher("admin").forward(request, response);
				}
				else
				{
					out.print("<b> Please Enter Both Filde Same Password </b>");
					request.getRequestDispatcher("NewAdmin.jsp").include(request, response);
				}
				}
				else
				{
					out.print("<b> Please Enter or 8 and more thane 8 charcter Password </b>");
					request.getRequestDispatcher("NewAdmin.jsp").include(request, response);
				}			
				rs.close();
			}
			
			else
			{
				out.print("<b> User Name Alrade Exsiste </b>");
				request.getRequestDispatcher("NewAdmin.jsp").include(request, response);
			}
		}
		catch(Exception e)
		{
			out.print(e);
		}
		finally
		{
			con.close();
			ps.close();
		}

	}
	

	private void resetpass(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		PrintWriter out=response.getWriter();
		Connection con=null;
		PreparedStatement ps=null;

		String username=(request.getParameter("username")).toLowerCase();
		
		try
		{ 
			con=datasource.getConnection();
			String sql="SELECT Question from admin where Username=?";
			ps=con.prepareStatement(sql);
			ps.setString(1, username);
			ResultSet rs=ps.executeQuery();
			if(rs.next())
			{
				int que =rs.getInt("Question");
				String question=null;
				switch(que)
				{
				case 1:
					question="Enter Your Home Name";
					break;

				case 2:
					question="Enter Your First Phone Number";
					break;

				case 3:
					question="Enter Your Best Teacher Name";
					break;

				case 4:
					question="Enter Your Favorite Song Name";
					break;
				}
				request.setAttribute("Question", question);
				request.setAttribute("username", username);
				request.getRequestDispatcher("resatpassword.jsp").forward(request, response);
			}
			else
			{
				out.print("<b> User Name Not Exsiste </b>");
				request.getRequestDispatcher("forgetpassword.jsp").include(request, response);
			}
			
			rs.close();
		}
		catch(Exception e)
		{
			out.print(e);
		}
		finally
		{
			ps.close();
			con.close();
		}


		
	}



}
