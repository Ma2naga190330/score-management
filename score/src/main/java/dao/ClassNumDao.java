package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.ClassNum;
import bean.School;

public class ClassNumDao extends Dao{
	public List<ClassNum> get(School school)throws Exception{
		List<ClassNum> c_list = new ArrayList<>();
		Connection connection = getConnection();
		PreparedStatement statement = null;
		try {
			statement = connection.prepareStatement("select * from class_num where school_cd = ? and class_flag = true");
			statement.setString(1,school.getCd());
			ResultSet rSet = statement.executeQuery();
			
			SchoolDao sDao = new SchoolDao();
			while(rSet.next()) {
				ClassNum classNum = new ClassNum();
				classNum.setClassNum(rSet.getString("class_num"));
				classNum.setClassName(rSet.getString("class_name"));
				classNum.setClassFlag(rSet.getBoolean("class_flag"));
				classNum.setSchool(sDao.get(rSet.getString("school_cd")));
				c_list.add(classNum);
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
		return c_list;
	}
	public ClassNum get(String class_num,School school)throws Exception{
		ClassNum classNum = new ClassNum();
		Connection connection = getConnection();
		PreparedStatement statement = null;
		try {
			statement = connection.prepareStatement("select * from class_num where class_num=? and school_cd = ?");
			statement.setString(1,class_num);
			statement.setString(2,school.getCd());
			ResultSet rSet = statement.executeQuery();
			
			SchoolDao sDao = new SchoolDao();
			if(rSet.next()) {
				classNum.setClassNum(rSet.getString("class_num"));
				classNum.setClassName(rSet.getString("class_name"));
				classNum.setClassFlag(rSet.getBoolean("class_flag"));
				classNum.setSchool(sDao.get(rSet.getString("school_cd")));
			}else {
				classNum = null;
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
		return classNum;
	}
	public List<String> filter(School school)throws Exception{
		List<String> list = new ArrayList<>();
		Connection connection = getConnection();
		PreparedStatement statement = null;
		try {
			statement = connection.prepareStatement("select class_num from class_num where school_cd = ? and class_flag = true order by class_num");
			statement.setString(1,school.getCd());
			ResultSet rSet = statement.executeQuery();
			while (rSet.next()) {
				list.add(rSet.getString("class_num"));
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
		return list;
	}
	
	public boolean save(ClassNum classNum) throws Exception{
		Connection connection = getConnection();
		PreparedStatement statement = null;
		int count = 0;
		try {
			ClassNum old = get(classNum.getClassNum(),classNum.getSchool());
			
			if(old == null) {
				statement = connection.prepareStatement("insert into class_num(class_num, class_name, school_cd, class_flag) values(?, ?, ?, ?)");
				statement.setString(1, classNum.getClassNum());
				statement.setString(2, classNum.getClassName());
				statement.setBoolean(4,true);
				statement.setString(3, classNum.getSchool().getCd());
			}else {
				statement = connection.prepareStatement("update class_num set class_name = ?, class_flag = ? where class_num = ? and school_cd = ?");
				statement.setString(1, classNum.getClassName());
				statement.setBoolean(2,true);
				statement.setString(3, classNum.getClassNum());
				statement.setString(4, classNum.getSchool().getCd());
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
	
	public boolean delete(ClassNum classNum) throws Exception{
		Connection connection = getConnection();
		PreparedStatement statement = null;
		try {
			String school_cd = classNum.getSchool().getCd();
			String class_num = classNum.getClassNum();
			statement = connection.prepareStatement("update CLASS_NUM set class_flag = false where class_num = ? and school_cd = ?");
			statement.setString(2, school_cd);
            statement.setString(1, class_num);
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
	
//	public boolean save(ClassNum classNum) throws Exception{
//		
//	}
//	public boolean save(ClassNum classNum, String newClassNum)throws Exception{
//		
//	}

