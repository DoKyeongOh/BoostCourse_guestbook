package org.edwith.webbe.guestbook.util;

import java.sql.*;
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
	    	try {
				if (!this.conn.isValid(0)) 
					if (!this.setConnection(getConnection())) return false;
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			}
			
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
				try {
					this.pstmt.executeUpdate(this.sql);
					return true;
				} catch (Exception e2) {
					System.out.println("execute - " + sql + " - " + e2);
					return false;
				}
			}
			
			return true;
		}
		
		public void close() {
			try {
				this.sql = "";
				this.rs.close();
				this.pstmt.close();
				this.conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			close();
		}
		
	}
	
	private DBElements dbElements;
	
	public DBUtil () {
		initDbElements();
	}
	
	public void initDbElements() {
		if (dbElements != null) dbElements.close();
		dbElements = new DBElements();
		dbElements.setConnection(getConnection());
	}
	
	private Connection getConnection(){
        return getConnection("jdbc:mysql://localhost:3306/exam","DBA","1234");
    }

    private Connection getConnection(String dbURL, String dbId, String dbPassword){
    	Connection conn = null;

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
    
    public DBElements sqlExecute(String sql) {
    	try {
			if (!dbElements.conn.isValid(0)) 
				if (!dbElements.setConnection(getConnection())) return null;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
    	
    	if (!dbElements.setStmt(sql)) return null;
    	if (!dbElements.execute()) return null;
    	
    	return dbElements;
    }

    
    public List<String> getTableNames() {
    	List<String> tables = new ArrayList<String>();
    	sqlExecute("show tables");
    	if (dbElements == null) return null;
    	
    	try {
			while (dbElements.rs.next()) {
				tables.add(dbElements.rs.getString(1));
			}
		} catch (SQLException e) {
			System.out.println("getTableNames - " + e);
		}
    	
    	return tables;
    }
    
    public boolean createTable(String ddl) {
    	if (sqlExecute(ddl) == null) return false;
    	else return true;
    }
}




































