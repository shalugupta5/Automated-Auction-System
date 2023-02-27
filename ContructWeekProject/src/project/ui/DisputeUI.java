package project.ui;

import java.util.List;

import java.util.Scanner;

import project.dao.DisputeOperation;
import project.dao.DisputeOperationImpl;
import project.dto.Buyers;
import project.dto.Dispute;
import project.dto.DisputeImpl;
import project.exception.NoRecordFoundException;
import project.exception.SomeThingWrongException;



public class DisputeUI {

	private DisputeOperation disputeOperation;
	private String scanner;
	
	
	
	public DisputeUI(String string) {
		super();
		disputeOperation = new DisputeOperationImpl();
		this.scanner = string;
	}


	
	public void viewDisputeReport() throws NoRecordFoundException, SomeThingWrongException {
		// TODO Auto-generated method stub
		Dispute dispute=new DisputeImpl();
		disputeOperation.viewDisputeReport();
	}

}
