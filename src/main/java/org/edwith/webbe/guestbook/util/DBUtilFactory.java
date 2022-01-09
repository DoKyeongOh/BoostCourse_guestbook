package org.edwith.webbe.guestbook.util;

public class DBUtilFactory {
	public enum DBType {MySQL, Oracle}
	
	public DBUtil getInstance(DBType brand) {
		if ((brand) == DBType.MySQL) return MysqlConnector.getInstance();
		else if ((brand) == DBType.Oracle) return MysqlConnector.getInstance();
		else return MysqlConnector.getInstance();
	}
	
}
