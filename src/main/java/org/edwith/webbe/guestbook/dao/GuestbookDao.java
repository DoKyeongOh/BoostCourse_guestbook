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
	
	public GuestbookDao () {
		//
	}
	
    public List<Guestbook> getGuestbooks(){
        
        // 리스트를 불러오기 위한 조건
        // 1. DB에 관련 테이블 있는지 체크
        // 2. 관련 테이블 생성
        // 3. 쿼리문 실행 및 결과 불러오기
        // 4. 리스트 형태로 담기
    	
        List<Guestbook> list = new ArrayList<>();
        DBUtil dbutil = new DBUtil();
        String sql;
        
        if (!dbutil.findTable("guestbook")) {
        	sql = "CREATE TABLE `guestbook` ("
        			  + "`id` INT(11) NOT NULL AUTO_INCREMENT COMMENT 'guestbook id',"
        			  + "`name` TEXT NOT NULL COMMENT 'guestbook name',"
        			  + "`content` TEXT NOT NULL COMMENT 'guestbook content',"
        			  + "`regdate` DATETIME NULL DEFAULT NULL COMMENT '등록일',"
        			  + "PRIMARY KEY (`id`)) ENGINE=InnoDB DEFAULT CHARSET=utf8;";
        	if (!dbutil.exec(sql)) return null;
        }
        
        sql = "Select * from guestbook";
        ResultSet rs = dbutil.execQuery(sql);
        try {
			while (rs.next()) {
				list.add(new Guestbook(rs.getLong(1), rs.getString(2), rs.getString(3), rs.getDate(4)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

        return list;
    }

    public void addGuestbook(Guestbook guestbook){
        // 코드를 작성하세요.
    }

    
}
