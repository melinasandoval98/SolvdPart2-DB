package com.solvd.computerrepairservice.model;

public enum DataStorageTypes {
	HDD(1), SDD(2);

	private long dataStorageTypeID;

	DataStorageTypes(long dataStorageTypeID) {
		this.dataStorageTypeID = dataStorageTypeID;
	}

	public long getDataStorageTypeID() {
		return dataStorageTypeID;
	}

}
