package com.solvd.computerrepairservice.model;

public class ComputerProblemSubType {
	private long id;
	private ComputerProblemTypes computerProblemType;
	private String computerProblemDrescription;
	private Long serviceSubPrice;

	public ComputerProblemSubType(long id, ComputerProblemTypes computerProblemType, String computerProblemDrescription,
			Long serviceSubPrice) {
		super();
		this.id = id;
		this.computerProblemType = computerProblemType;
		this.computerProblemDrescription = computerProblemDrescription;
		this.serviceSubPrice = serviceSubPrice;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public ComputerProblemTypes getComputerProblemType() {
		return computerProblemType;
	}

	public void setComputerProblemType(ComputerProblemTypes computerProblemType) {
		this.computerProblemType = computerProblemType;
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

	@Override
	public String toString() {
		return "ComputerProblemSubType [id=" + id + ", computerProblemType=" + computerProblemType
				+ ", computerProblemDrescription=" + computerProblemDrescription + ", serviceSubPrice="
				+ serviceSubPrice + "]";
	}

}
