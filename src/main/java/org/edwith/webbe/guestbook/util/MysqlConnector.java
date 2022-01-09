package org.edwith.webbe.guestbook.util;

import java.sql.*;

public class MysqlConnector implements DBUtil {
	private DBParameter parameters = null;
	private Connection connection = null;
	private static MysqlConnector mysql = null;
	
	private MysqlConnector() {
		init();
	}
	
	public static DBUtil getInstance() {
		if (mysql == null) mysql = new MysqlConnector();
		return mysql;
	}

	public void setParameter() {
		if (parameters == null) parameters = new DBParameter();
		
//		parameters.setOption("?useUnicode=true&characterEncoding=utf8&useSSL=false&useLegacyDatetimeCode=false&serverTimezone=UTC");		
		parameters.setUrl("jdbc:mysql://localhost:3306/exam");
		parameters.setUserId("DBA");
		parameters.setUserPswd("1234");
		
		parameters.addOption("useUnicode=true");
		parameters.addOption("characterEncoding=utf8");
		parameters.addOption("useSSL=false");
		parameters.addOption("useLegacyDatetimeCode=false");
		parameters.addOption("serverTimezone=UTC");	
	}
	
	@Override
	public void init() {
		if (this.connection == null) {
			connection = getConnection();
		}
		
		try {
			if (!connection.isValid(0)) {
				connection = getConnection();
			}
			
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
	
	@Override
	public Connection getConnection(){
		setParameter();
		
    	String url = parameters.getUrl();
    	String id = parameters.getUserId();
    	String pswd = parameters.getUserPswd();
    	
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection= DriverManager.getConnection(url, id, pswd);
        }catch(Exception e){
			e.printStackTrace();
			return null;
        }
        return connection;
    }

	@Override
	public ResultSet execQuery(String sql) {
		init();
		PreparedStatement pstmt;
		try {
			pstmt = connection.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			return rs;
		} catch (SQLException e1) {
			e1.printStackTrace();
			return null;
		}
		
	}
	
	@Override
	public boolean execUpdate(String sql) {
		init();
		try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
			pstmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	

    
    
    

}




































