package dao;

import java.sql.Connection;

import javax.naming.InitialContext;
import javax.sql.DataSource;

import jakarta.annotation.Resource;

@Resource(name="jdbc/h2db",type=javax.sql.DataSource.class)
public class Dao {
	static DataSource ds;
	String url = "jdbc:h2:/home/ubuntu/h2data/point";
	
	public Connection getConnection() throws Exception{
		if (ds==null) {
			InitialContext ic=new InitialContext();
			ds=(DataSource)ic.lookup(url);
		}
		return ds.getConnection();
	}
}
