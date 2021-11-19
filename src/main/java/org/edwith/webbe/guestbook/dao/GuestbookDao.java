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
		init();
	}
	
    public List<Guestbook> getGuestbooks(){
        List<Guestbook> list = new ArrayList<>();

    	DBUtil dbUnit = new DBUtil();
    	dbUnit.getTableNames();

        return list;
    }

    public void addGuestbook(Guestbook guestbook){
        // 코드를 작성하세요.
    }
    
    public boolean init() {
    	
    	return true;
    }

    
}
