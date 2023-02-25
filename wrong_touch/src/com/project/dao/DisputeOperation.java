package com.project.dao;

import com.project.dto.Dispute;
import com.project.exception.NoRecordFoundException;
import com.project.exception.SomeThingWrongException;

public interface DisputeOperation {

	void viewDisputeReport() throws NoRecordFoundException, SomeThingWrongException;

	

}
