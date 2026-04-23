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
import bean.TestListStudent;

public class TestListStudentDao extends Dao {
	private String baseSql = "select * from test where school_cd = ?";
	
	private List<TestListStudent> postFilter(ResultSet rSet) throws Exception {
		List<TestListStudent> list = new ArrayList<>();
		try {
			while (rSet.next()) {
				TestListStudent tls = new TestListStudent();
				School school = new School();
				school.setCd(rSet.getString("school_cd"));
				SubjectDao sDao = new SubjectDao();
				Subject sub = sDao.get(rSet.getString("subject_cd"),school);
				tls.setSubjectName(sub.getName());
				tls.setSubjectCd(sub.getCd());
				tls.setNum(rSet.getInt("no"));
				tls.setPoint(rSet.getInt("point"));
				list.add(tls);
			}
		} catch (SQLException | NullPointerException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public List<TestListStudent> filter(Student student)throws Exception{
		List<TestListStudent> list = new ArrayList<>();
		Connection connection = getConnection();
		PreparedStatement statement = null;
		String sqle = "select * from test where student_no = ? and school_cd = ?";
		
		try {
			statement = connection.prepareStatement(sqle);
			statement.setString(1, student.getNo());
			statement.setString(2, student.getSchool().getCd());
			ResultSet rSet = statement.executeQuery();
			list = postFilter(rSet);
		} catch (Exception e) {
			throw e;
		} finally {
			if (statement != null) {
				try {
					statement.close();
				}catch (SQLException sql) {
					throw sql;
				}
			}
		}
		return list;
	}
}
