package org.attedanceApp;

import java.sql.Date;

public class CourseWiseAttendanceModel {
	private String Cname;
	public String getCname() {
		return Cname;
	}
	public void setCname(String cname) {
		Cname = cname;
	}
	public Date getD() {
		return d;
	}
	public void setD(Date d) {
		this.d = d;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	private Date d;
	private int count;
	
	
	

}
