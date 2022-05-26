package com.solvd.computerrepairservice.model;

public class ComputerProblemSubType {
	private long id;
	private long computerProblemTypeID;
	private String computerProblemDrescription;
	private Long serviceSubPrice;

	public ComputerProblemSubType(long id, String computerProblemDrescription, Long serviceSubPrice,
			long computerProblemType) {
		super();
		this.id = id;
		this.computerProblemTypeID = computerProblemType;
		this.computerProblemDrescription = computerProblemDrescription;
		this.serviceSubPrice = serviceSubPrice;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getComputerProblemTypeID() {
		return computerProblemTypeID;
	}

	public void setComputerProblemTypeID(long computerProblemTypeID) {
		this.computerProblemTypeID = computerProblemTypeID;
	}

	public String getComputerProblemDrescription() {
		return computerProblemDrescription;
	}

	public void setComputerProblemDrescription(String computerProblemDrescription) {
		this.computerProblemDrescription = computerProblemDrescription;
	}

	public Long getServiceSubPrice() {
		return serviceSubPrice;
	}

	public void setServiceSubPrice(Long serviceSubPrice) {
		this.serviceSubPrice = serviceSubPrice;
	}

}
