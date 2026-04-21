package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Student;
import bean.Subject;
import bean.Test;

public class TestDao extends Dao {
	private String baseSql = "select * from test where school_cd=?";
	public Test get(Student student, Subject subject, School school, int no) throws Exception {
		Test test = null;
		Connection connection = getConnection();
		PreparedStatement statement = null;
		try {
			statement = connection.prepareStatement("select * from test where student_no = ? and subject_cd = ? and school_cd = ? and no = ?");
			statement.setString(1, student.getNo());
			statement.setString(2, subject.getCd());
			statement.setString(3, school.getCd());
			statement.setInt(4, no);
			
			ResultSet rSet = statement.executeQuery();
			
			if (rSet.next()) {
				test = new Test();
				test.setStudent(student);
				test.setSubject(subject);
				test.setSchool(school);
				test.setNo(no);
				test.setPoint(rSet.getInt("point"));
			}
		}catch (Exception e) {
			throw e;
		}finally {
			if (statement != null) {
				try {
					statement.close();
				}catch (SQLException sqle) {
					throw sqle;
				}
			}
			if (connection != null) {
				try {
					connection.close();
				}catch (SQLException sqle) {
					throw sqle;
				}
			}
		}
		return test;
	}
	
	private List<Test> postFilter(ResultSet rSet, School school) throws Exception {
		List<Test> list = new ArrayList<>();
		try {
			while (rSet.next()) {
				Student student = new Student();
				student.setNo(rSet.getString("no"));
				student.setName(rSet.getString("name"));
				student.setEntYear(rSet.getInt("ent_year"));
				student.setClassNum(rSet.getString("class_num"));
				
				Test test = new Test();
				test.setStudent(student);
				test.setPoint(rSet.getInt("point"));
				
				list.add(test);
			}
		}catch (SQLException | NullPointerException e){
				e.printStackTrace();
			
		}
		return list;
	}
	
	public List<Test> filter(int entYear, String classNum, Subject subject, int num, School school) throws Exception {
		System.out.println(entYear);
		List<Test> list = new ArrayList<>();
		Connection connection = getConnection();
		PreparedStatement statement = null;
		ResultSet rSet = null;
		String sql = "select * from test join student on test.student_no = student.no where student.school_cd = ? and ent_year = ? and test.class_num = ? and subject_cd = ? and test.no = ?";
		
		try {
			statement = connection.prepareStatement(sql);
			statement.setString(1, school.getCd());
			statement.setInt(2, entYear);
			statement.setString(3, classNum);
			statement.setString(4, subject.getCd());
			statement.setInt(5, num);
			
			rSet = statement.executeQuery();
			
			list = postFilter(rSet, school);
		}catch (Exception e) {
			throw e;
		}finally {
			if (statement != null) {
				try {
					statement.close();
				}catch (SQLException sqle) {
					throw sqle;
				}
			}
			if (connection != null) {
				try {
					connection.close();
				}catch (SQLException sqle) {
					throw sqle;
				}
			}
		}
		return list;
	}
	
	public boolean save(List<Test> list) throws Exception {
		Connection connection = getConnection();
		try {
			for (Test test : list) {
				save(test, connection);
			}
		}catch (Exception e) {
			throw e;
		}finally {
			if (connection != null) {
				try {
					connection.close();
				}catch (SQLException sqle) {
					throw sqle;
				}
			}
		}
		return true;
	}
	
	private boolean save(Test test, Connection connection) throws Exception {
		PreparedStatement statement = null;
		int count = 0;
		try {
			Test old = get(test.getStudent(), test.getSubject(), test.getSchool(), test.getNo());
			
			if (old == null) {
				statement = connection.prepareStatement("insert into test(student_no, subject_cd, school_cd, no, point)");
				statement.setString(1, test.getStudent().getNo());
				statement.setString(2, test.getSubject().getCd());
				statement.setString(3, test.getSchool().getCd());
				statement.setInt(4, test.getNo());
				statement.setInt(5, test.getPoint());
			}else {
				statement = connection.prepareStatement("update test set point = ?" + "where student_no = ? and subject_cd = ? and school_cd = ? and no = ?");
				statement.setInt(1, test.getPoint());
				statement.setString(2, test.getStudent().getNo());
				statement.setString(3, test.getSubject().getCd());
				statement.setString(4, test.getSchool().getCd());
				statement.setInt(5, test.getNo());
			}
			count = statement.executeUpdate();
		}catch (Exception e) {
			throw e;
		}finally {
			if (statement != null) {
				try {
					statement.close();
				}catch (SQLException sqle) {
					throw sqle;
				}
			}
			if (connection != null) {
				try {
					connection.close();
				}catch (SQLException sqle) {
					throw sqle;
				}
			}
		}
		if (count > 0) {
			return true;
		}else {
			return false;
		}
	}
}