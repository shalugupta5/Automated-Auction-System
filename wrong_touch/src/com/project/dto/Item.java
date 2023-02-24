package com.project.dto;

import java.time.LocalDate;

public interface Item {
	public Integer getItemId();
	public void setItemId(Integer itemId);
	public String getItemName();
	public void setItemName(String itemName);
	public Double getMRP();
	public void setMRP(Double mRP);
	public LocalDate getMFGDate();
	public void setMFGDate(LocalDate mFGDate);
	public String getCategory();
	public void setCategory(String category);
	
}
