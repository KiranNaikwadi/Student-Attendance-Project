package org.attedanceService;

import java.util.List;

import org.attedanceModel.StudentModel;
import org.attendaceRepository.StudentRepository;


public class StudentService {
	StudentRepository studRepo=new StudentRepository();
	
	public int addStudent(StudentModel model,String cname) {
		
		return studRepo.isAddStudent(model,cname)?1:0;
	}
	public List<StudentModel> viewStudent() {
		return studRepo.isViewStudent();
	}
	public boolean UpdateStudent(StudentModel model,String name,int id,String email,String contact) {
		return studRepo.isUpdateStudent(model,name,id,email,contact)?true:false;
	}
	
	public boolean DeleteStudent(StudentModel model,String name,int id) {
		return studRepo.isDeleteStudent(model,name,id)?true:false;
	}
	
}
