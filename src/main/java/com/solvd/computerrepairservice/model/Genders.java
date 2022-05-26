package com.solvd.computerrepairservice.model;

public enum Genders {
	FEMALE(2), MALE(1);

	private int genderID;

	Genders(int genderID) {
		this.genderID = genderID;
	}

	public int getGenderID() {
		return genderID;
	}

}
