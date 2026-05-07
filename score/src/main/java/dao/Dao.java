package dao;

import java.sql.Connection;

import javax.naming.InitialContext;
import javax.sql.DataSource;

import jakarta.annotation.Resource;

@Resource(name="jdbc/h2",type=javax.sql.DataSource.class)
public class Dao {
	static DataSource ds;
	String url = "jdbc:comp/env/jdbc/h2";
	
	public Connection getConnection() throws Exception{
		if (ds==null) {
			InitialContext ic=new InitialContext();
			ds=(DataSource)ic.lookup(url);
		}
		return ds.getConnection();
	}
}
