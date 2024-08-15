package org.attendaceRepository;
import java.util.ArrayList;
import java.util.List;
import org.attedanceModel.StudentModel;

public class StudentRepository extends DBConfig {
    public boolean isAddStudent(StudentModel model,String Cname) {
    	int Cid=this.getCourseIdByCourse(model.getCname());
		try {
			stmt=conn.prepareStatement("insert into Student values('0',?,?,?,?)");
			stmt.setString(1,model.getSname());
			stmt.setString(2,model.getEmail());
			stmt.setString(3,model.getContact());
			stmt.setInt(4,Cid);
		
			int value=stmt.executeUpdate();
			if(value>0) {
				return true;
			}
			else {
				return false;
			}
		}
		catch(Exception ex){
			System.out.println("Error is"+ex);
			return false;
		}
	}

	public List<StudentModel> isViewStudent() {
		
		List<StudentModel> studentList = new ArrayList<StudentModel>();
		try {
			stmt=conn.prepareStatement("select * from Student");
			rs=stmt.executeQuery();
			while(rs.next()) {
				
				StudentModel student = new StudentModel();
				student.setSid(rs.getInt(1));
				student.setSname(rs.getString(2));
				student.setEmail(rs.getString(3));
				student.setContact(rs.getString(4));
				student.setCid(rs.getInt(5));
				
                studentList.add(student);
			}
			return studentList;
			
		}
		catch(Exception ex) {
			System.out.println("Error is"+ex);
			return null;
		}
		
	}

	public boolean isUpdateStudent(StudentModel model,String name,int id,String email,String contact) {
		try {
			stmt=conn.prepareStatement("update Student set Sname=?,Email=?,Contact=? where Sid=?");
			stmt.setString(1,name);
			stmt.setString(2,email);
			stmt.setString(3,contact);
			//stmt.setInt(4, Cid);
		    stmt.setInt(5,id);
			stmt.executeUpdate();
		}
		catch(Exception ex) {
			System.out.println("Error is"+ex);
		}
		return false;
	}
	public boolean isDeleteStudent(StudentModel model,String name,int id) {
		try {
			stmt=conn.prepareStatement("delete from Student where Sname=? and Sid=?");
			stmt.setString(1,name);
			stmt.setInt(2,id);
			stmt.executeUpdate();
			return true;
		}
		catch(Exception ex) {
			System.out.println("Error is"+ex);
			return false;
		}
		
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
