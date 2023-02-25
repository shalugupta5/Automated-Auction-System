package com.project.ui;

import java.util.List;
import java.util.Scanner;

import com.project.dao.DisputeOperation;
import com.project.dao.DisputeOperationImpl;
import com.project.dto.Buyers;
import com.project.dto.Dispute;
import com.project.dto.DisputeImpl;
import com.project.exception.NoRecordFoundException;
import com.project.exception.SomeThingWrongException;



public class DisputeUI {

	private DisputeOperation disputeOperation;
	private Scanner scanner;
	
	
	
	public DisputeUI(Scanner scanner) {
		super();
		disputeOperation = new DisputeOperationImpl();
		this.scanner = scanner;
	}


	
	public void viewDisputeReport() throws NoRecordFoundException, SomeThingWrongException {
		// TODO Auto-generated method stub
		Dispute dispute=new DisputeImpl();
		disputeOperation.viewDisputeReport();
	}

}
