package org.edwith.webbe.guestbook.dao;

import org.edwith.webbe.guestbook.dto.Guestbook;
import org.edwith.webbe.guestbook.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GuestbookDao {
	DBUtil dbUnit;
	
	public GuestbookDao () {
    	dbUnit = new DBUtil();
		init();
	}
	
    public List<Guestbook> getGuestbooks(){
        List<Guestbook> list = new ArrayList<>();

    	dbUnit.getTableNames();

        return list;
    }

    public void addGuestbook(Guestbook guestbook){
        // 코드를 작성하세요.
    }
    
    public boolean init() {
    	createGuestbookTable();
    	return true;
    }
    
    public boolean createGuestbookTable() {
    	List<String> tableList = this.dbUnit.getTableNames();
    	
    	if (tableList.contains("guestbook")) return true;
    	
    	String ddl = "";
    	ddl = "CREATE TABLE `guestbook` (";
    	ddl += " `id` INT(11) NOT NULL AUTO_INCREMENT COMMENT 'guestbook id',";
    	ddl += "`content` TEXT NOT NULL COMMENT 'guestbook content',";
    	ddl += "`regdate` DATETIME NULL DEFAULT NULL COMMENT '등록일',";
    	ddl += "PRIMARY KEY (`id`)) ENGINE=InnoDB DEFAULT CHARSET=utf8;";
    	
    	this.dbUnit.createTable(ddl);
    	
    	return true;
    }

    
}
