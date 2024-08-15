package org.attendaceRepository;

import java.util.ArrayList;
import java.util.List;

import org.attedanceModel.BatchModel;
import org.attedanceModel.StudentModel;

public class BatchRepository extends DBConfig {

	
	public boolean isAddBatch(BatchModel bmodel) {
      try {
			stmt=conn.prepareStatement("insert into Batch values('0',?,?)");
			stmt.setString(1,bmodel.getBname());
			stmt.setString(2,bmodel.getYear());
			
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

	public boolean isBatchPresent(BatchModel bmodel, String bname) {
		try {
			stmt=conn.prepareStatement("select *from Batch where Bname=?");
			stmt.setString(1,bname);
			rs=stmt.executeQuery();
			return rs.next();
			}
		catch(Exception ex) {
			System.out.println("Error is"+ex);
			return false;
		}
		
	}
	
	public List<BatchModel> isviewBatch(){
		List<BatchModel> batchList = new ArrayList<BatchModel>();
		try {
			stmt=conn.prepareStatement("select * from Batch");
			rs=stmt.executeQuery();
			while(rs.next()) {
				
				BatchModel batch = new BatchModel();
				batch.setBid(rs.getInt(1));
				batch.setBname(rs.getString(2));
				batch.setYear(rs.getString(3));
				
                batchList.add(batch);
			}
			return batchList;
			
		}
		catch(Exception ex) {
			System.out.println("Error is"+ex);
			return null;
		}
	}


}
