package com.lowes.bankingapp.fundstransfer.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class FundsTransfer {

	@NotBlank(message = "srcAccId cannot be blank")
	private String srcAccId;

	@NotBlank(message = "trgtAccId cannot be blank")
	private String trgtAccId;

	private String description;

	@NotNull
	@Min(value = 1, message = "Minimum amount > 0")
	private double amount;

	public String getSrcAccId() {
		return srcAccId;
	}

	public void setSrcAccId(String srcAccId) {
		this.srcAccId = srcAccId;
	}

	public String getTrgtAccId() {
		return trgtAccId;
	}

	public void setTrgtAccId(String trgtAccId) {
		this.trgtAccId = trgtAccId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}



}