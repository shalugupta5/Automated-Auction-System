package com.project.dto;

import java.time.LocalDate;
import java.util.Objects;



public class ItemImpl implements Item {

	private Integer itemId;
	private String itemName;
	private Double MRP;
	private LocalDate MFGDate;
	private String category;
	
	public ItemImpl() {}

	public ItemImpl(Integer itemId, String itemName, Double mRP, LocalDate mFGDate, String category) {
		super();
		this.itemId = itemId;
		this.itemName = itemName;
		MRP = mRP;
		MFGDate = mFGDate;
		this.category = category;
	}

	public Integer getItemId() {
		return itemId;
	}

	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public Double getMRP() {
		return MRP;
	}

	public void setMRP(Double mRP) {
		MRP = mRP;
	}

	public LocalDate getMFGDate() {
		return MFGDate;
	}

	public void setMFGDate(LocalDate mFGDate) {
		MFGDate = mFGDate;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	@Override
	public int hashCode() {
		return Objects.hash(MFGDate, MRP, category, itemId, itemName);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ItemImpl other = (ItemImpl) obj;
		return Objects.equals(MFGDate, other.MFGDate) && Objects.equals(MRP, other.MRP)
				&& Objects.equals(category, other.category) && Objects.equals(itemId, other.itemId)
				&& Objects.equals(itemName, other.itemName);
	}

	@Override
	public String toString() {
		return "ItemImpl [itemId=" + itemId + ", itemName=" + itemName + ", MRP=" + MRP + ", MFGDate=" + MFGDate
				+ ", category=" + category + "]";
	}
	
	
	
}
