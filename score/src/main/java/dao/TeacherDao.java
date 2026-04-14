package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bean.Teacher;

public class TeacherDao extends Dao{
	// ログイン認証
	public Teacher loginUser(Teacher t) throws Exception {
		Teacher user = new Teacher();
		Connection connection = getConnection();
		PreparedStatement statement = null;
		try {
			statement = connection.prepareStatement("select * from teacher where id = ? and password = ?");
			statement.setString(1, t.getId());
			statement.setString(2, t.getPassword());
			ResultSet result = statement.executeQuery();
			SchoolDao sDao = new SchoolDao();
			if (result.next()) {
				user.setId(result.getString("id"));
				user.setName(result.getString("name"));
				user.setPassword(result.getString("password"));
				user.setSchool(sDao.get(result.getString("school_cd")));
				user.setAuthenticated(true);
			}else {
				user = null;
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
		return user;
	}
}
