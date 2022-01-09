package org.edwith.webbe.guestbook.util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public interface DBUtil {
	
	class DBParameter {
		private String Url = "";
		private String userId = "";
		private String userPswd = "";
		private List<String> Options;

		
		public String getUrl() {
			if (this.Url != "") {
				this.Url = this.Url + "?";
				for (String op : getOptions()) {
					this.Url = this.Url + op + "&";
				}
			}
				
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

		public String getUserPswd() {
			return userPswd;
		}

		public void setUserPswd(String userPswd) {
			this.userPswd = userPswd;
		}
		
		public void addOption(String Op) {
			if (this.Options == null) this.Options = new ArrayList<String>();
			if (!this.Options.contains(Op)) this.Options.add(Op);
		}
		
		public List<String> getOptions() {
			if (this.Options == null) this.Options = new ArrayList<String>();
			return this.Options;
		}
		
		public void init() {
			setUserId("");
			setUserPswd("");
			setUrl("");
		}
		
		public boolean checkParameter(DBParameter parameter) {
			if (userId == "") return false;
			if (userPswd == "") return false;
			if (Url == "") return false;
			return true;
		}
	}

	public void init();
	public Connection getConnection(); 
	public ResultSet execQuery(String sql);
	public boolean execUpdate(String sql);
	
	
}
