package org.edwith.webbe.guestbook.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Driver;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;

public class DBUtil {
	
	public class DBElements {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";
		
		public boolean setConnection (Connection c) {
			if (c == null) return false;
			
			this.conn = c;
			return true;
		}

		public boolean setStmt (String sql) {
			if (this.conn == null) return false;
			if (sql == "" || sql == null) return false;
			
			// initialize
			this.pstmt = null;
			this.sql = sql;
			try {
				this.pstmt = conn.prepareStatement(this.sql);
			} catch (SQLException e) {
				System.out.println("setStmt - " + sql + " - " + e);
				return false;
			}
			return true;
		}

		public boolean execute () {
			if (this.pstmt == null || this.conn == null) return false;
			
			try {
				this.rs = this.pstmt.executeQuery(this.sql);
			} catch (SQLException e) {
				System.out.println("execute - " + sql + " - " + e);
				return false;
			}
			
			return true;
		}
		
		public void close() {
			this.conn = null;
			this.pstmt = null;
			this.rs = null;
			this.sql = "";
			close();
		}
		
	}
	
	private Connection getConnection(){
        return getConnection("jdbc:mysql://localhost:3306/exam","DBA","1234");
        //?useSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC
    }

    private Connection getConnection(String dbURL, String dbId, String dbPassword){
    	Connection conn = null;
    	// Timezone, SSL
    	String option = "?useUnicode=true&characterEncoding=utf8&useSSL=false&useLegacyDatetimeCode=false&serverTimezone=UTC";
    	dbURL += option;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");//com.mysql.cj.jdbc.Driver
            conn= DriverManager.getConnection(dbURL, dbId, dbPassword);
        }catch(Exception ex){
			System.out.println("getConnection - " + ex);
        }
        return conn;
    }

    public List<String> getTableNames() {
    	List<String> tables = new ArrayList<String>();
    	DBElements elements = new DBElements();
    	
    	if (!elements.setConnection(getConnection())) return null;
    	if (!elements.setStmt("show tables")) return null;
    	if (!elements.execute()) return null;
    	try {
			while (elements.rs.next()) {
				tables.add(elements.rs.getString(1));
			}
		} catch (SQLException e) {
			System.out.println("getTableNames - " + e);
		}
    	return tables;
    }
}




































