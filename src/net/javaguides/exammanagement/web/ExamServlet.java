package net.javaguides.exammanagement.web;

import java.io.IOException;


import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.javaguides.exammanagement.dao.ExamDAO;
import net.javaguides.exammanagement.model.Exam;


@WebServlet("/")
public class ExamServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private ExamDAO examDAO;
    
    public void init() {
    	examDAO = new ExamDAO();
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
    		throws ServletException, IOException {
		
		doGet(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		String action = request.getServletPath();
		
		try {
			switch(action) {
			case"/new":
				showNewForm(request, response);
				break;
			case"/insert":
				insertExam(request, response);
				break;
			case"/delete":
				deleteExam(request, response);
				break;
			case"/edit":
				showEditForm(request, response);
				break;
			case"/update":
				updateExam(request, response);
				break;
			default:
				listExam(request, response);
				break;
			
			}
		}catch(SQLException ex) {
			throw new ServletException(ex);
		}
	}
	private void listExam(HttpServletRequest request,HttpServletResponse response) 
			throws SQLException, IOException, ServletException {
		List<Exam> listExam = examDAO.selectAllExams();
		request.setAttribute("listExam",listExam);
		RequestDispatcher dispatcher = request.getRequestDispatcher("exam-list.jsp");
		dispatcher.forward(request, response);
		
	}
	private void showNewForm(HttpServletRequest request,HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("exam-form.jsp");
		dispatcher.forward(request, response);
	}
	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException,ServletException, IOException{
		int examid = Integer.parseInt(request.getParameter("examid"));
		Exam existingExam = examDAO.selectExam(examid);
		RequestDispatcher dispatcher = request.getRequestDispatcher("exam-form.jsp");
		request.setAttribute("exam", existingExam);
		dispatcher.forward(request, response);
	}
	private void insertExam(HttpServletRequest request, HttpServletResponse response)
		throws SQLException, IOException {
		String subjectname = request.getParameter("subjectname");
		String dateandtime = request.getParameter("dateandtime");
		String type = request.getParameter("type");
		Exam newExam = new Exam(subjectname, dateandtime, type);
		examDAO.insertExam(newExam);;
		response.sendRedirect("list");
		
	}
	private void updateExam(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException{
		int examid = Integer.parseInt(request.getParameter("examid"));
		String subjectname = request.getParameter("subjectname");
		String dateandtime = request.getParameter("dateandtime");
		String type = request.getParameter("type");
		
		Exam book= new Exam(examid,subjectname, dateandtime, type);
		examDAO.updateExam(book);
		response.sendRedirect("list");
		
		
	}
	private void deleteExam(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException{
		int examid = Integer.parseInt(request.getParameter("examid"));
		examDAO.deleteExam(examid);
		response.sendRedirect("list");
		
	}
	
	
	

}
