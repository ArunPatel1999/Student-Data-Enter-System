package com.arun;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
@WebServlet("/studentDBcontrol")
public class studentDBcontrol extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private studentDB studentdb;
	@Resource(name="jdbc/project")
	private DataSource datasource;
	
	@Override
	public void init() throws ServletException {
		super.init();

		try {
			
			studentdb=new studentDB(datasource);
		}
		catch(Exception e)
		{
			throw new ServletException(e); 
		}
	}



	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

			try {
				
				String thecommand = request.getParameter("command");
				if(thecommand==null)
					thecommand="LIST";
				
				switch(thecommand)
				{
				case "LIST":
					liststudent(request,response);
					break;
					
				case "LOAD":
					loadstudent(request,response);
					break;
				case "UPDATE":
					updateStudent(request,response);
					break;
					
				case "ADD":
					addstudent(request,response);
					break;
					
				case "DELETE":
					detetestudent(request,response);
					break;
					
				default:
					liststudent(request,response);
				}
			} catch (Exception e) {
			
				throw new ServletException(e);
				
			}
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
		}

	private void detetestudent(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		String id=request.getParameter("studentID");
		studentdb.deletestudent(id);
		liststudent(request, response);
		
		
	}



	private void updateStudent(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		int id=Integer.parseInt(request.getParameter("studentID"));
		String fname=request.getParameter("fname");
		String lname=request.getParameter("lname");
		String email=request.getParameter("email");
		
		student thestudent=new student(id,fname, lname, email);
		studentdb.updatestudent(thestudent);
		liststudent(request, response);
	
	}



	private void loadstudent(HttpServletRequest request, HttpServletResponse response) throws Exception {
	
		String id=request.getParameter("studentID");
		student thestudent = studentdb.getStud(id);
		
		request.setAttribute("THE_STUDENT", thestudent);
		request.getRequestDispatcher("/update.jsp").forward(request, response);
	}



	private void addstudent(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String fname=request.getParameter("fname");
		String lname=request.getParameter("lname");
		String email=request.getParameter("email");
		
		student thestudent = new student(fname, lname, email);
		
		studentdb.addstudent(thestudent);
		
		liststudent(request, response);
	}



	private void liststudent(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		List<student> students=studentdb.getStudents();
		request.setAttribute("STUDENTS_LIST", students);
		request.getRequestDispatcher("student_details.jsp").forward(request, response);
	}

}
