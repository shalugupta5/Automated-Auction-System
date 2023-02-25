package com.project.dto;

import java.time.LocalDate;
import java.util.Objects;

public class DisputeImpl implements Dispute {
	private Integer buyerID;
	private Integer sellerID;
	private Integer itemID;
	private String description;
	private LocalDate Date;
	
	public DisputeImpl() {}
	
	public DisputeImpl(Integer buyerID, Integer sellerID, Integer itemID, String description, LocalDate date) {
		super();
		this.buyerID = buyerID;
		this.sellerID = sellerID;
		this.itemID = itemID;
		this.description = description;
		Date = date;
	}
	public Integer getBuyerID() {
		return buyerID;
	}
	public void setBuyerID(Integer buyerID) {
		this.buyerID = buyerID;
	}
	public Integer getSellerID() {
		return sellerID;
	}
	public void setSellerID(Integer sellerID) {
		this.sellerID = sellerID;
	}
	public Integer getItemID() {
		return itemID;
	}
	public void setItemID(Integer itemID) {
		this.itemID = itemID;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public LocalDate getDate() {
		return Date;
	}
	public void setDate(LocalDate date) {
		Date = date;
	}
	@Override
	public int hashCode() {
		return Objects.hash(Date, buyerID, description, itemID, sellerID);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DisputeImpl other = (DisputeImpl) obj;
		return Objects.equals(Date, other.Date) && Objects.equals(buyerID, other.buyerID)
				&& Objects.equals(description, other.description) && Objects.equals(itemID, other.itemID)
				&& Objects.equals(sellerID, other.sellerID);
	}
	@Override
	public String toString() {
		return "DisputeImpl [buyerID=" + buyerID + ", sellerID=" + sellerID + ", itemID=" + itemID + ", description="
				+ description + ", Date=" + Date + "]";
	}
	
	
	
}
