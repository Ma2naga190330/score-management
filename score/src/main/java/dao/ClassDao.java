package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bean.Class;
import bean.School;


public class ClassDao extends Dao{
	private String baseSql = "select * from class where school_cd = ?";
	public Class get(String Class_num, School school)throws Exception{
		Class classD = new Class();
		Connection connection = getConnection();
		PreparedStatement statement = null;
		try{
			statement = connection.prepareStatement("select * from class_num where class_num = ? and school_cd = ?");
			statement.setString(1, Class_num);
			statement.setString(2,school.getCd());
			ResultSet rSet = statement.executeQuery();
			
			SchoolDao sDao = new SchoolDao();
			if(rSet.next()) {
				classD.setClassNum(rSet.getString("class_num"));
				classD.setClassName(rSet.getString("class_name"));
				classD.setClassFlag(rSet.getBoolean("class_flag"));
				classD.setSchool(sDao.get(rSet.getString("school_cd")));
			}else {
				classD = null;
			}
		}catch (Exception e) {
			throw e;
		}finally {
			if(statement != null) {
				try {
					statement.close();
				}catch (SQLException sqle) {
					throw sqle;
				}
			}
			if(connection != null) {
				try {
					connection.close();
				}catch (SQLException sqle) {
					throw sqle;
				}
			}
		}
		return classD;
	}
	
	public boolean save(Class clas) throws Exception{
		Connection connection = getConnection();
		PreparedStatement statement = null;
		int count = 0;
		try {
			Class old = get(clas.getClassNum(),clas.getSchool());
			
			if(old == null) {
				statement = connection.prepareStatement("insert into class_num(class_num, class_name, school_cd, class_flag) values(?, ?, ?, ?)");
				statement.setString(1, clas.getClassNum());
				statement.setString(2, clas.getClassName());
				statement.setBoolean(3,clas.getClassFlag());
				statement.setString(4, clas.getSchool().getCd());
			}else {
				statement = connection.prepareStatement("update class_num set class_name = ?, class_flag = ? where class_num = ? and school_cd = ?");
				statement.setString(1, clas.getClassNum());
				statement.setString(2, clas.getClassName());
				statement.setBoolean(3,clas.getClassFlag());
				statement.setString(4, clas.getSchool().getCd());
			}
			count = statement.executeUpdate();
		}catch(Exception e) {
			throw e;
		}finally {
			if(statement != null) {
				try {
					statement.close();
				} catch (SQLException sqle) {
					throw sqle;
				}
			}
			if(connection != null) {
				try {
					connection.close();
				}catch (SQLException sqle) {
					throw sqle;
				}
			}
		}
		if(count > 0) {
			return true;
		}else {
			return false;
		}
	}
	
	public boolean delete(Class clas) throws Exception{
		Connection connection = getConnection();
		PreparedStatement statement = null;
		try {
			statement = connection.prepareStatement("update class_num set class_flag = false where class_num = ? and school_cd = ?");
			statement.setString(1, clas.getClassNum());
            statement.setString(2, clas.getSchool().getCd());
			int num = statement.executeUpdate();
			if(num > 0) {
				return true;
			}else {
				return false;
			}
		} catch (Exception e) {
			throw e;
		}finally {
			if(statement != null) {
				try {
					statement.close();
				}catch (SQLException sqle) {
					throw sqle;
				}
			}
		}
	}
}
