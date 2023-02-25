package com.project.dto;

import java.time.LocalDate;

public interface Dispute {

	public Integer getBuyerID();
	public void setBuyerID(Integer buyerID);
	public Integer getSellerID();
	public void setSellerID(Integer sellerID);
	public Integer getItemID();
	public void setItemID(Integer itemID);
	public String getDescription();
	public void setDescription(String description);
	public LocalDate getDate();
	public void setDate(LocalDate date);
		
	
	
	
	
}
