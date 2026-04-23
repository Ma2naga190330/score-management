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
					tls.setStudentNo(rSet.getString("student_no"));
					tls.setStudentName(rSet.getString("name"));
					tls.setClassNum(rSet.getString("class_num"));
//					map.put(no, rSet.getInt("point"));
					list.add(tls);
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
		String sql = "select * from test join student on test.student_no = student.no where student.school_cd = ? and ent_year = ? and test.class_num = ? and subject_cd = ?";
		
		try {
			statement = connection.prepareStatement(sql);
			statement.setString(1, school.getCd());
			statement.setInt(2, entYear);
			statement.setString(3, classNum);
			statement.setString(4, subject.getCd());
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
