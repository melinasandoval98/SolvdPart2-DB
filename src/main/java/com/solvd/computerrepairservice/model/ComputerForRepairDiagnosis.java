package com.solvd.computerrepairservice.model;

public class ComputerForRepairDiagnosis {
	private long computerForRepairDiagnosisID;
	private ComputerForRepair computerForRepair;
	private ComputerProblemSubType computerProblemSubType;

	public ComputerForRepairDiagnosis(long computerForRepairDiagnosisID, ComputerForRepair computerForRepair,
			ComputerProblemSubType computerProblemSubType) {
		super();
		this.computerForRepairDiagnosisID = computerForRepairDiagnosisID;
		this.computerForRepair = computerForRepair;
		this.computerProblemSubType = computerProblemSubType;
	}

	public long getComputerForRepairDiagnosisID() {
		return computerForRepairDiagnosisID;
	}

	public void setComputerForRepairDiagnosisID(long computerForRepairDiagnosisID) {
		this.computerForRepairDiagnosisID = computerForRepairDiagnosisID;
	}

	public ComputerForRepair getComputerForRepair() {
		return computerForRepair;
	}

	public void setComputerForRepair(ComputerForRepair computerForRepair) {
		this.computerForRepair = computerForRepair;
	}

	public ComputerProblemSubType getComputerProblemSubType() {
		return computerProblemSubType;
	}

	public void setComputerProblemSubType(ComputerProblemSubType computerProblemSubType) {
		this.computerProblemSubType = computerProblemSubType;
	}

	@Override
	public String toString() {
		return "ComputerForRepairDiagnosis [computerForRepairDiagnosisID=" + computerForRepairDiagnosisID
				+ ", computerForRepair=" + computerForRepair + ", computerProblemSubType=" + computerProblemSubType
				+ "]";
	}

}
