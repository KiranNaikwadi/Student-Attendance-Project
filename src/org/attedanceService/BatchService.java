package org.attedanceService;

import java.util.List;

import org.attedanceModel.BatchModel;
import org.attedanceModel.CourseModel;
import org.attendaceRepository.BatchRepository;

public class BatchService {
	BatchRepository bRepo=new BatchRepository() ;
	
public int AddBatch(BatchModel bmodel,String bname) {
		
		return bRepo.isBatchPresent(bmodel,bname)?-1:bRepo.isAddBatch(bmodel)?1:0;
	}
public List<BatchModel> viewBatch() {
	return bRepo.isviewBatch();
}
}
