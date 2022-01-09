package org.edwith.webbe.guestbook.util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBUtil {

	class DBParameter {

		private String connectorType = "";
		private String Url = "";
		private String userId = "";
		private String userPswd = "";
		private String Option = "";
		public String getconnectorType() {
			return connectorType;
		}

		public void setConnectorType(String connectorType) {
			this.connectorType = connectorType;
		}

		public String getUrl() {
			return Url;
		}

		public void setUrl(String dbUrl) {
			this.Url = dbUrl;
		}

		public String getUserId() {
			return userId;
		}

		public void setUserId(String userId) {
			this.userId = userId;
		}

		public String getuUerPswd() {
			return userPswd;
		}

		public void setUserPswd(String userPswd) {
			this.userPswd = userPswd;
		}

		public String getOption() {
			return Option;
		}

		public void setDbOption(String Option) {
			this.Option = Option;
		}
		
		public void initial() {
			setConnectorType("");
			setUserId("");
			setUserPswd("");
			setUrl("");
			setDbOption("");
		}
		
		public boolean checkParameter(DBParameter parameter) {
			if (connectorType == "") return false;
			if (userId == "") return false;
			if (userPswd == "") return false;
			return true;
		}
	}

	public DBParameter parameters = null;

	public DBUtil() {
		parameters = new DBParameter();
		setCustomParameter();
	}
	public Connection getConnection(){
		setCustomParameter();
		return getConnection(parameters);
    }

	private Connection getConnection(DBParameter param){
		Connection conn = null;
		
    	String url = parameters.getUrl() + parameters.getOption();
    	String id = parameters.getUserId();
    	String pswd = parameters.getuUerPswd();
    	
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn= DriverManager.getConnection(url, id, pswd);
        }catch(Exception e){
			e.printStackTrace();
			return null;
        }
        return conn;
    }
	
	public void setCustomParameter() {
		parameters.setConnectorType("mysql");
		parameters.setUrl("jdbc:mysql://localhost:3306/exam");
		parameters.setUserId("DBA");
		parameters.setUserPswd("1234");
		
		// 타임존, ssl 옵션, utf-8 설정되어있음.
		parameters.setDbOption("?useUnicode=true&characterEncoding=utf8&useSSL=false&useLegacyDatetimeCode=false&serverTimezone=UTC");
	}
	
	public ResultSet execQuery(String sql) {
		Connection conn = getConnection();
		if (conn == null) return null;
		
		PreparedStatement pstmt;
		try {
			pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			return rs;
		} catch (SQLException e1) {
			e1.printStackTrace();
			return null;
		}
		
	}
	
	public boolean exec(String sql) {
		try (
				Connection conn = getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean findTable(String tablename) {
		List<String> list = new ArrayList<String>();
		String sql = "show tables";
		
		ResultSet rs = execQuery(sql);
		if (rs == null) return false;
		
		
		try {
			while(rs.next()) {
				// 테이블 컬럼의 인덱스는 1부터인가보다
				list.add(rs.getString(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		
		if (list.contains(tablename)) return true;
		else return false;
	}
    
    
    

}




































