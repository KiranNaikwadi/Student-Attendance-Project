package org.attedanceService;

import java.util.List;

import org.attedanceApp.CourseWiseAttendanceModel;
import org.attedanceModel.AttendanceModel;
import org.attedanceModel.DateModel;
import org.attedanceModel.DateWiseAttendaceModel;
//import org.attedanceModel.CourseWiseAttendanceModel;
import org.attendaceRepository.AttendanceRepository;

public class AttendanceService {
	AttendanceRepository aRepo=new AttendanceRepository();
	
	public int takeAttendance(AttendanceModel amodel,String Sname,String Cname) {
		return aRepo.istakeAttendance(amodel,Sname,Cname)?1:0;
		
	}

	public List<CourseWiseAttendanceModel> CourseWiseAttendance() {
	return aRepo.iscoursewiseAttendance();
		
	}
	public List<DateWiseAttendaceModel> DateWiseAttendace(String Sname){
		return aRepo.isdatewiseAttendance(Sname);
	}
	
	public List<DateModel>studentWiseAttendance(int day,int month,int year)
	{
		return aRepo.viewStudentWiseAttendane(day,month,year);
	}

	

}
