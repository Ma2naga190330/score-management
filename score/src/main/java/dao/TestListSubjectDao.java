package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import bean.School;
import bean.Subject;
import bean.TestListSubject;

public class TestListSubjectDao extends Dao{
	private String baseSql = "select * from subject where school_cd = ?";
	
	private List<TestListSubject> postFilter(ResultSet rSet)throws Exception{
		List<TestListSubject> list = new ArrayList<>();
		Map<String, TestListSubject> map = new HashMap<>();
		try {
			while (rSet.next()) {
				String no = rSet.getString("no");
				TestListSubject tls = map.get(no);
				if(tls == null) {
					tls = new TestListSubject();
					tls.setEntYear(rSet.getInt("ent_year"));
					tls.setStudentNo(no);
					tls.setStudentName(rSet.getString("name"));
					tls.setClassNum(rSet.getString("class_num"));
					map.put(no, tls);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public List<TestListSubject> filter(int entYear, String classNum, Subject subject, School school)throws Exception{
		List<TestListSubject> list = new ArrayList<>();
		Connection connection = getConnection();
		PreparedStatement statement = null;
		String sql = "select * from test join student on test.student_no = student.no where student.school_cd = ? and ent_year = ? and test.class_num = ? and subject_cd = ? and test.no = ?";
		
		try {
			statement = connection.prepareStatement(baseSql + sql);
			statement.setString(1, subject.getCd());
			statement.setString(2, school.getCd());
			statement.setInt(3, entYear);
			statement.setString(4, classNum);
			statement.setString(5, school.getCd());
			ResultSet rSet = statement.executeQuery();
			list = postFilter(rSet);
		} catch (Exception e) {
			throw e;
		} finally {
			if (statement != null) {
				try {
					statement.close();
				}catch (Exception sqle) {
					throw sqle;
				}
			}
		}
		return list;
	}
}
