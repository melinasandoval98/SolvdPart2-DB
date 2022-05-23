package com.solvd.computerrepairservice.model;

public enum ComputerProblemTypes {
	SOFTWARE_PROBLEM("software"), HARDWARE_PROBLEM("hardware");

	private String computerProblemTypes;

	private ComputerProblemTypes(String computerProblemTypes) {
		this.computerProblemTypes = computerProblemTypes;
	}

	public String getComputerProblemTypes() {
		return computerProblemTypes;
	}

}
