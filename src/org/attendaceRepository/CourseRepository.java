package org.attendaceRepository;

import java.util.ArrayList;
import java.util.List;

import org.attedanceModel.CourseModel;
import org.attedanceModel.StudentModel;
import org.attedanceModel.ViewCourseWiseModel;

public class CourseRepository extends DBConfig {

public boolean isAddCourse(CourseModel cmodel) {
	try {
		//int Cid=this.getCourseIdByCourse(Cname1);
		stmt=conn.prepareStatement("insert into Course values('0',?)");
		stmt.setString(1,cmodel.getCname());
		
		int value=stmt.executeUpdate();
		if(value>0) {
			return true;
		}
		else {
			return false;
		}
	}
	catch(Exception ex) {
		System.out.println("Eroor is"+ex);
		return false;
	}
	
	
}
public boolean isCoursePresent(CourseModel cmodel,String cname) {
		try {
			stmt=conn.prepareStatement("select *from Course where Cname=?");
			stmt.setString(1,cname);
			rs=stmt.executeQuery();
			return rs.next();
			}
		catch(Exception ex) {
			System.out.println("Error is"+ex);
			return false;
		}
		
	}
public List<CourseModel> isViewCourse() {
	List<CourseModel> courseList = new ArrayList<CourseModel>();
	try {
		stmt=conn.prepareStatement("select * from Course");
		rs=stmt.executeQuery();
		while(rs.next()) {
			
			CourseModel course = new CourseModel();
			course.setCid(rs.getInt(1));
			course.setCname(rs.getString(2));
			
             courseList.add(course);
		}
		return courseList;
		
	}
	catch(Exception ex) {
		System.out.println("Error is"+ex);
		return null;
	}
}
public List <ViewCourseWiseModel>isviewCoursewiseStudent(String Cname) {
	List<ViewCourseWiseModel> clist=new ArrayList<ViewCourseWiseModel>();
	try {
		int Cid=this.getCourseIdByCourse(Cname);
		stmt=conn.prepareStatement("Select Sid,Sname,Email,Contact from Student where Cid=?");
		stmt.setInt(1, Cid);
		rs=stmt.executeQuery();
	
		while(rs.next()){
			ViewCourseWiseModel vmodel=new ViewCourseWiseModel();
			vmodel.setSid(rs.getInt(1));
			vmodel.setSname(rs.getString(2));
			vmodel.setEmail(rs.getString(3));
			vmodel.setContact(rs.getString(4));
			
			clist.add(vmodel);
		}
		return clist;
		
		}
	
	catch(Exception ex){
		System.out.println("Error is"+ex);
	}
	return null;
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

}
