package com.solvd.computerrepairservice.model;

public enum Genders {
	FEMALE(1), MALE(2);

	private int genderID;

	Genders(int genderID) {
		this.genderID = genderID;
	}

	public int getGenderID() {
		return genderID;
	}

}
