package com.solvd.computerrepairservice.model;

public enum OperatingSystems {
	WINDOWS(1, "7", "8", "9", "10", "11"),
	LINUX_UBUNTU(2, "16.04 LTS Xenial Xerus", "14.04.6 LTS	Trusty Tahr", "14.04.5 LTS Trusty Tahr"),
	LINUX_FEDORA(3, "32", "33", "34", "35", "36"), LINUX_MINT(4, "19", "20", "20.1"),
	MAC_OS(5, "10.14 Mojave", "10.15 Catalina", "11.0 Big Sur", "12.0 Monterey");

	private long operatingSystemID;

	private String[] versions;

	private OperatingSystems(long oSID, String... versions) {
		this.operatingSystemID = oSID;
		this.versions = versions;
	}

	public String[] getVersions() {
		return versions;
	}

	public long getOperatingSystemID() {
		return operatingSystemID;
	}

}
