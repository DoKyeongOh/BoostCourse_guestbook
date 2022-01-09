package org.edwith.webbe.guestbook.dao;

import org.edwith.webbe.guestbook.dto.Guestbook;
import org.edwith.webbe.guestbook.util.DBUtil;
import org.edwith.webbe.guestbook.util.DBUtilFactory;
import org.edwith.webbe.guestbook.util.DBUtilFactory.DBType;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GuestbookDao {
	DBUtilFactory dbFactory = null;
	DBUtil dbutil = null;
	List<Guestbook> guestbooks = null;
	int contentCount = 1;
	
	public GuestbookDao() {
		dbFactory = new DBUtilFactory();
		dbutil = dbFactory.getInstance(DBType.MySQL);
			
	}
	
    public List<Guestbook> getGuestbooks(){
        List<Guestbook> list = new ArrayList<Guestbook>();
        createGuestbookTable();
        
        String sql;
        sql = "Select id, name, content, regdate from guestbook";
        ResultSet rs = dbutil.execQuery(sql);
        try {
			while (rs.next()) {
				list.add(new Guestbook(rs.getLong(1), rs.getString(2), rs.getString(3), rs.getString(4)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
        
        guestbooks = list;
        return list;
    }

    public void addGuestbook(Guestbook guestbook){
    	createGuestbookTable();
    	String sql = "insert into guestbook values(" 
    						+ Long.toString(guestbook.getId()) + ",\""
    						+ guestbook.getName() + "\",\""
    						+ guestbook.getContent() + "\","
    						+ guestbook.getRegdate() + ");";
    	dbutil.execUpdate(sql);
    }
	
	public boolean findTable(String tablename) {
		List<String> list = new ArrayList<String>();
		String sql = "show tables";
		
		ResultSet rs = dbutil.execQuery(sql);
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
    
	public boolean createGuestbookTable() {
        if (!findTable("guestbook")) {
        	String sql = "CREATE TABLE `guestbook` ("
        			  + "`id` INT(11) NOT NULL AUTO_INCREMENT COMMENT 'guestbook id',"
        			  + "`name` TEXT NOT NULL COMMENT 'guestbook name',"
        			  + "`content` TEXT NOT NULL COMMENT 'guestbook content',"
        			  + "`regdate` TEXT NULL DEFAULT NULL COMMENT '등록일',"
        			  + "PRIMARY KEY (`id`)) ENGINE=InnoDB DEFAULT CHARSET=utf8;";
        	if (!dbutil.execUpdate(sql)) return false;
        }
        return true;
	}
    
}
