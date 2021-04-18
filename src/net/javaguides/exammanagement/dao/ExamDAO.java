package net.javaguides.exammanagement.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import net.javaguides.exammanagement.model.Exam;

public class ExamDAO {
	private String jdbcURL = "jdbc:mysql://localhost:3306/exam4?useSSL=false";
	private String jdbcUsername = "root";
	private String jdbcPassword = "maybe12345A*";
	
	private static final String INSERT_EXAMS_SQL = "INSERT INTO exams"+ "  (subjectname, dateandtime, type) VALUES "
	        + " (?, ?, ?);";
	
	private static final String SELECT_EXAM_BY_ID = "select examid,subjectname,dateandtime,type from exams where examid =?";
	private static final String SELECT_ALL_EXAMS = "select * from exams";
	private static final String DELETE_EXAMS_SQL = "delete from exams where examid = ?;";
	private static final String UPDATE_EXAMS_SQL = "update exams set subjectname = ?,dateandtime= ?, type =? where examid = ?;";
	
	public ExamDAO() {
		
	}
	
	protected Connection getConnection() {
		Connection connection = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
		}catch(SQLException e) {
			e.printStackTrace();
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		return connection;
	}
	
	public void insertExam(Exam exam)throws SQLException {
		System.out.println(INSERT_EXAMS_SQL);
		//Try with resource statement will auto close the connection.
		try(Connection connection = getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(INSERT_EXAMS_SQL)){
			preparedStatement.setString(1,exam.getSubjectname());
			preparedStatement.setString(2,exam.getDateandtime());
			preparedStatement.setString(3,exam.getType());
			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();
		}catch(SQLException e) {
			printSQLException(e);
			
			}
	}
	
	public Exam selectExam(int examid) {
		Exam exam = null;
		//Step 1: establish the connection:
		try(Connection connection = getConnection();
				//Step 2: Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_EXAM_BY_ID);) {
			preparedStatement.setInt(1, examid);
			//Step 3: execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();
			//Step 4: Process the ResultSet object
			while(rs.next()) {
				String subjectname=rs.getString("subjectname");
				String dateandtime=rs.getString("dateandtime");
				String type=rs.getString("type");
				exam=new Exam(examid,subjectname,dateandtime,type);
			}
		}catch(SQLException e) {
			printSQLException(e);
				
			}
		return exam;
			
		}
	
	public List<Exam> selectAllExams() {
		List<Exam> exams = new ArrayList<>();
		//Step 1: Establish connection:
		try(Connection connection = getConnection();
				//Step 2: Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_EXAMS);) {
			    System.out.println(preparedStatement);
			    //Step 3: Execute the query or update query
			    ResultSet rs = preparedStatement.executeQuery();
			    
			    //Step 4: Process the ResultSet object
			    while(rs.next()) {
			    	int examid = rs.getInt("examid");
			    	String subjectname = rs.getString("subjectname");
			    	String dateandtime = rs.getString("dateandtime");
			    	String type = rs.getString("type");
			    	exams.add(new Exam(examid,subjectname,dateandtime,type));
			    }
		}catch(SQLException e) {
			    	printSQLException(e);
	     }
			    return exams;
	}
	
	public boolean deleteExam(int examid) throws SQLException {
		boolean rowDeleted;
		try(Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_EXAMS_SQL);) {
			statement.setInt(1, examid);
			rowDeleted = statement.executeUpdate() > 0;
		}
		return rowDeleted;
		}
	
	public boolean updateExam(Exam exam)throws SQLException{
		boolean rowUpdated;
		try(Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(UPDATE_EXAMS_SQL);) {
			statement.setString(1, exam.getSubjectname());
			statement.setString(2, exam.getDateandtime());
			statement.setString(3, exam.getType());
			statement.setInt(4, exam.getExamid());
			
			rowUpdated = statement.executeUpdate() > 0;
			
		}
		return rowUpdated;
	}
		
	private void printSQLException(SQLException ex) {
		for(Throwable e: ex) {
			if(e instanceof SQLException) {
				e.printStackTrace(System.err);
				System.err.println("SQLState: "+ ((SQLException) e).getSQLState());
				System.err.println("Error Code: "+ ((SQLException) e).getErrorCode());
				System.err.println("Message: "+ e.getMessage());
				Throwable t = ex.getCause();
				while(t != null) {
					System.out.println("Cause: "+ t);
					t=t.getCause();
				}
				
			}
		}
	 }
	}
	

	

