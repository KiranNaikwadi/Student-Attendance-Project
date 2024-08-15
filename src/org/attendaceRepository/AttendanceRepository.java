package org.attendaceRepository;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.attedanceApp.CourseWiseAttendanceModel;
import org.attedanceModel.AttendanceModel;
import org.attedanceModel.DateModel;
import org.attedanceModel.DateWiseAttendaceModel;
//import org.attedanceModel.CSWModel;
import org.attedanceModel.StudentModel;
public class AttendanceRepository extends DBConfig{

	public boolean istakeAttendance(AttendanceModel amodel,String Sname, String cname) {
		try {
			int Sid=this.getStudentIdByStudent(Sname);
			//int Cid=this.getCourseIdByCourse(cname);
			Date date=this.getDate();
			if(Sid!=0) {
			stmt=conn.prepareStatement("insert into Attendance values('0',?,?,?)");
			stmt.setInt(1,Sid);
			//stmt.setInt(2, Cid);
			stmt.setDate(2,date);
			stmt.setString(3,amodel.getStatus());
			int value=stmt.executeUpdate();
			if(value>0) {
				return true;
			}
			else {
				return false;
			}
			}
		}
		catch(Exception ex) {
			System.out.println("Error is"+ex);
			return false;
		}
		return false;
	}

	public int getStudentIdByStudent(String Sname) {
		try {
			stmt=conn.prepareStatement("select * from Student where Sname=?");
			stmt.setString(1, Sname);
			rs=stmt.executeQuery();
			if(rs.next()) {
				return rs.getInt(1);
		    }
			else {
				return -1;
			}
		}
		catch(Exception ex) {
			System.out.println("Error is"+ex);
		}
		return 0;
		
	}
	private int getCourseIdByCourse(String Cname) {
		try {
			stmt=conn.prepareStatement("select * from Course where Cname=?");
			stmt.setString(1, Cname);
			rs=stmt.executeQuery();
			if(rs.next()) {
				return rs.getInt(1);
			}
			else {
				return -1;
			}
		}
		catch(Exception ex) {
			System.out.println("Error is"+ex);
			return 0;
		}
		
		
	}
	private Date getDate() {
		try {
			stmt=conn.prepareStatement("select curDate()");
//			stmt.setString(1,Date);
			rs=stmt.executeQuery();
			if(rs.next()) {
				return rs.getDate(1);
			}
			else {
				return null;
			}
		}
		catch(Exception ex) {
			System.out.println("Error is"+ex);
			return null;
		}
	}
	
public List< CourseWiseAttendanceModel>iscoursewiseAttendance() {
		
		List< CourseWiseAttendanceModel> cswList = new ArrayList< CourseWiseAttendanceModel>();
		try {
			stmt=conn.prepareStatement("Select C.Cname,count(S.Sid),A.date from Course C inner join Student S on C.Cid=S.Cid inner join Attendance A on S.Sid=A.Sid group by C.Cname,A.date;");
			rs=stmt.executeQuery();
			while(rs.next()) {
				
				 CourseWiseAttendanceModel cswModel = new  CourseWiseAttendanceModel();
				 cswModel.setCname(rs.getString(1));
				 cswModel.setCount(rs.getInt(2));
				 cswModel.setD(rs.getDate(3));
				
				  cswList.add(cswModel);
			}
			return cswList;
		}
		catch(Exception ex) {
			System.out.println("Error is"+ex);
			return null;
		}
	}

public List<DateWiseAttendaceModel> isdatewiseAttendance(String sname) {
	List<DateWiseAttendaceModel> DWAList=new ArrayList<DateWiseAttendaceModel>();
	try {
		int Sid=this.getStudentIdByStudent1(sname);
		stmt=conn.prepareStatement("Select date,status from Attendance where Sid=?");
		stmt.setInt(1, Sid);
		rs=stmt.executeQuery();
		while(rs.next()) {
			DateWiseAttendaceModel dwaModel=new DateWiseAttendaceModel();
			dwaModel.setDate(rs.getDate(1));
			dwaModel.setStatus(rs.getString(2));
			DWAList.add(dwaModel);
		}
		return DWAList;
	}
	catch(Exception ex) {
		System.out.println("Error is"+ex);
	}
	return null;
}
public int getStudentIdByStudent1(String sname) {
	try {
		stmt=conn.prepareStatement("select * from Student where Sname=?");
		stmt.setString(1, sname);
		rs=stmt.executeQuery();
		if(rs.next()) {
			return rs.getInt(1);
	    }
		else {
			return -1;
		}
	}
	catch(Exception ex) {
		System.out.println("Error is"+ex);
	}
	return 0;
	
}


public List<DateModel>viewStudentWiseAttendane(int day,int month,int year)
{
	
	List<DateModel> dlist=new ArrayList();
	try
	{
		stmt=conn.prepareStatement("select s.Sid,s.Sname,a.Status from Student s inner join attendance a on s.Sid=a.Sid where day(a.date)=? && month(a.date)=?&& year(a.date)=?");
		stmt.setInt(1,day);
		stmt.setInt(2,month);
		stmt.setInt(3,year);
		rs=stmt.executeQuery();
		while(rs.next())
		{
			DateModel dmodel=new DateModel();
			dmodel.setSid(rs.getInt(1));
			dmodel.setSname(rs.getString(2));
			dmodel.setStatus(rs.getString(3));
			dlist.add(dmodel);
		}
		return dlist;
	}
	catch(Exception ex)
	{
		System.out.println("error is"+ex);
		return null;
	}
}


}
