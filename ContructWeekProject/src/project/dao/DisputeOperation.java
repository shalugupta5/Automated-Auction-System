package project.dao;

import project.dto.Dispute;
import project.exception.NoRecordFoundException;
import project.exception.SomeThingWrongException;

public interface DisputeOperation {

	void viewDisputeReport() throws NoRecordFoundException, SomeThingWrongException;

	

}
