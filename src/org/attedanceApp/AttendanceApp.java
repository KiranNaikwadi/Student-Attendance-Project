package org.attedanceApp;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

import org.attedanceModel.AttendanceModel;
import org.attedanceModel.BatchModel;

import org.attedanceModel.CourseModel;
import org.attedanceModel.DateModel;
import org.attedanceModel.DateWiseAttendaceModel;

import org.attedanceModel.StudentModel;
import org.attedanceModel.ViewCourseWiseModel;
import org.attedanceService.AttendanceService;
import org.attedanceService.BatchService;
import org.attedanceService.CourseService;

import org.attedanceService.StudentService;
import org.student.helper.PathHelper;
public class AttendanceApp {

	//private static final String Email = null;

	public static void main(String[] args) {
		String name;
		int id;
		
		Scanner sc=new Scanner(System.in);
		StudentService ss=new StudentService();
		StudentModel sm=new StudentModel();
		CourseService cs=new CourseService();
		CourseModel cm=new CourseModel();
		BatchService bs=new BatchService();
		BatchModel bm=new BatchModel();
		AttendanceService as=new AttendanceService();
		AttendanceModel am=new AttendanceModel();
		
		
		PathHelper phelp=new PathHelper();
		int choice;
		boolean b;
		
		do {
			
			System.out.println("1.Add Student\n2.Add Course\n3.Add Batch\n4.Take Attendance\n5.Coursewise Attendance\n6.Search Student wise Course"
					+ "\n7.Datewise Attendace\n8 StudentWise Attendance");
			
			System.out.println("Enter your choice");
			choice=sc.nextInt();
			
			switch(choice) {
			case 1:
				  System.out.println("1.ADD\n2.View\n3.Update\n4.Delete");
				  System.out.println("enter your choice");
				  int x=sc.nextInt();
				  switch(x) {
				       
				  case 1:sc.nextLine();
					  System.out.println("Add Student name");
					   String Sname=sc.nextLine();
					   System.out.println("Enter email");
					   String Email=sc.nextLine();
					   System.out.println("Enter Contact");
					   String Contact=sc.nextLine();
					   System.out.println("Enter Course");
					   String cname=sc.nextLine();
					   StudentModel model=new StudentModel();
					   model.setSname(Sname);
					   model.setEmail(Email);
					   model.setContact(Contact); 
					   model.setCname(cname);
					   int result=ss.addStudent(model,cname);
					   System.out.println((result==1)?"Student Added Sucess.....":(result==-1)?"Student already present":"Some error is there");
					   break;
				 case 2:System.out.println("View Student details");	 
				   
				      List<StudentModel> slist = ss.viewStudent();
				      for (StudentModel studentModel : slist) {
					
				    	  System.out.println(studentModel.getSid()+"\t"+studentModel.getSname()+"\t"+studentModel.getEmail()+"\t"+studentModel.getContact());
					}
				      
				       break;
				 case 3:
					 sc.nextLine();
					 System.out.println("Update Student Details");
				        System.out.println("enter name");
				         name=sc.next();
				        System.out.println("Enter id");
				        id=sc.nextInt();
				        sc.nextLine();
				        System.out.println("Enter email");
				        String email=sc.nextLine();
				        System.out.println("Enter contact");
				        String contact=sc.nextLine();
				        
				        
				        b=ss.UpdateStudent(sm,name,id,email,contact);
				        break;
				 
				 case 4:
					    sc.nextLine();
					    System.out.println("Delete student name and id");
					    name=sc.nextLine();
					    id=sc.nextInt();
					    b=ss.DeleteStudent(sm,name,id);
					    break;
                       default:
						  System.out.println("Wrong choice");
				  }
				break;
	//Add course		
	case 2:sc.nextLine();
		System.out.println("Enter course name");
	        String name1=sc.nextLine();
	        CourseModel cmodel=new CourseModel();
	        cmodel.setCname(name1);
	        int result=cs.AddCourse(cmodel, name1);
	        if(result==-1) {
	        	System.out.println("Course is present");
	        }
	        else if(result==1) {
	        	System.out.println("Course Added Sucessfully");
	        }
	        else {
	        	System.out.println("Something wrong");
	        }
	       break;

//Add Batch
	case 3:sc.nextLine();
	       System.out.println("Enter batch name");
	       String bname=sc.nextLine();
	       System.out.println("Enter Batch Year");
	       String byear=sc.nextLine();
	       BatchModel bmodel=new BatchModel();
	       bmodel.setBname(bname);
	       bmodel.setYear(byear);
	       result=bs.AddBatch(bmodel, bname);
	       if(result==-1) {
	    	   System.out.println("Batch is present");
	       }
	       else if(result==1) {
	    	   System.out.println("Batch Added Sucessfully");
	       }
	       else {
	    	   System.out.println("Something Wrong");
	       }
	       break;
	       
	case 4:sc.nextLine();
	       System.out.println("Take Attendance");
	       System.out.println("Enter student,status");
	       String Sname=sc.nextLine();
	       String Cname=sc.nextLine();
	        String Status=sc.next();
	        AttendanceModel amodel=new AttendanceModel();
            amodel.setSname(Sname);  
	       amodel.setCname(Cname);
	       amodel.setStatus(Status);
	       result=as.takeAttendance(amodel, Sname,Cname);
	       if(result==1) {
	        	System.out.println("Attendance is add");
	        }
	        else {
	        	System.out.println("Something wrong");
	        }
	       break;
	       
	case 5:sc.nextLine();
	       System.out.println("Show course wise Attendance");
	       List<CourseWiseAttendanceModel> cswlist = as.CourseWiseAttendance();
		      for (CourseWiseAttendanceModel cswModel : cswlist) {
			        System.out.println(cswModel.getCname()+"\t"+cswModel.getCount()+"\t"+cswModel.getD());
			}
		    break; 
		    
   case 6:sc.nextLine();
          System.out.println("Search Student by Course Wise");
          System.out.println("Enter course");
          String Coursename=sc.nextLine();
          ViewCourseWiseModel vmodel=new ViewCourseWiseModel(); 
          List<ViewCourseWiseModel> vcmlist=cs.viewCoursewiseStudent(Coursename);
          for(ViewCourseWiseModel vcModel:vcmlist) {
        	  System.out.println(vcModel.getSid()+"\t"+vcModel.getSname()+"\t"+vcModel.getEmail()+"\t"+vcModel.getContact());
          }
           break;
           
   case 7:sc.nextLine();
           System.out.println("Show Datewise Attendance Of Student");
           System.out.println("Enter Student Name");
           String sname=sc.nextLine();
           DateWiseAttendaceModel Dmodel=new DateWiseAttendaceModel();
           List<DateWiseAttendaceModel> dwalist=as.DateWiseAttendace(sname);
           for(DateWiseAttendaceModel dmodel:dwalist) {
        	   System.out.println(dmodel.getDate()+"\t"+dmodel.getStatus());
           }
           break;
           
   case 8:// date model is used here
		sc.nextLine();
		System.out.println("1.student wise attendance on particular day");

		DateModel dmodel = new DateModel();
		System.out.println("enter day");
		int day = sc.nextInt();
		System.out.println("enter month");
		int month = sc.nextInt();
		System.out.println("enter year");
		int yr = sc.nextInt(); // year
		List<DateModel> dslist = as.studentWiseAttendance(day, month, yr);
		System.out.println("-----------------------------------------");
		System.out.println("| student_id  |    Sname    |   Status  |");
		System.out.println("-----------------------------------------");
		for (DateModel datemodel : dslist) {
			System.out.println("|\t" + datemodel.getSid() + "     |    " + datemodel.getSname() + "    |   "
					+ datemodel.getStatus() + " |");
		}
		System.out.println("-----------------------------------------");
		break;
	     
	       default:
				  System.out.println("Wrong choice");
				
				
    			 
			}
			
		}while(true);
		
	}

}
