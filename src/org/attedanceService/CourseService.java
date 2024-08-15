package org.attedanceService;

import java.util.List;

import org.attedanceModel.BatchModel;
import org.attedanceModel.CourseModel;
import org.attedanceModel.StudentModel;
import org.attedanceModel.ViewCourseWiseModel;
//import org.attedanceModel.StudentModel;
import org.attendaceRepository.CourseRepository;

public class CourseService {
	CourseRepository cRepo=new CourseRepository();
public int AddCourse(CourseModel cmodel,String cname) {
		
		return cRepo.isCoursePresent(cmodel,cname)?-1:cRepo.isAddCourse(cmodel)?1:0;
	}
   public List<CourseModel> viewCourse() {
	return cRepo.isViewCourse();
}
public List<ViewCourseWiseModel> viewCoursewiseStudent(String Cname) {
	return cRepo.isviewCoursewiseStudent(Cname);
}


}
