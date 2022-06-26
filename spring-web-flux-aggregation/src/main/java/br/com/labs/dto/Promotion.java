package br.com.labs.dto;

import java.time.LocalDate;

public class Promotion {

	private String type;

	private Double discount;

	private LocalDate endDate;
	
	public Promotion(String type, Double discount, LocalDate endDate) {
		super();
		this.type = type;
		this.discount = discount;
		this.endDate = endDate;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Double getDiscount() {
		return discount;
	}

	public void setDiscount(Double discount) {
		this.discount = discount;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

}
