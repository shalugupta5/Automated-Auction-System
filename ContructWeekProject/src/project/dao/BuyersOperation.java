package project.dao;

import java.sql.SQLException;
import java.util.List;

import project.dto.Buyers;
import project.exception.NoRecordFoundException;
import project.exception.SomeThingWrongException;

//import com.project.dto.Buyers;
//import com.project.exception.NoRecordFoundException;
//import com.project.exception.SomeThingWrongException;

public interface BuyersOperation {

	public void addBuyer(Buyers buyers) throws SQLException;
//	public void updateBuyerDetails(Buyers bys);

	public List<Buyers> getAllBuyers() throws SomeThingWrongException, NoRecordFoundException;

	public void Login(String username, String password) throws SomeThingWrongException, NoRecordFoundException;

	public void logout();
	
	
	
	
	
}
