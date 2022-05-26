package com.solvd.computerrepairservice.model;

import java.util.ArrayList;
import java.util.List;

public class ComputerForRepairDiagnosis {
	private long computerForRepairDiagnosisID;
	private ComputerForRepair computerForRepair;
	private List<ComputerProblemSubType> computerSubTypesProblems = new ArrayList<>();

	public ComputerForRepairDiagnosis(long computerForRepairDiagnosisID, ComputerForRepair computerForRepair,
			List<ComputerProblemSubType> computerSubTypesProblems) {
		super();
		this.computerForRepairDiagnosisID = computerForRepairDiagnosisID;
		this.computerForRepair = computerForRepair;
		this.computerSubTypesProblems = computerSubTypesProblems;
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

	public List<ComputerProblemSubType> getComputerSubTypesProblems() {
		return computerSubTypesProblems;
	}

	public void setComputerSubTypesProblems(List<ComputerProblemSubType> computerSubTypesProblems) {
		this.computerSubTypesProblems = computerSubTypesProblems;
	}

	@Override
	public String toString() {
		return "ComputerForRepairDiagnosis [computerForRepairDiagnosisID=" + computerForRepairDiagnosisID
				+ ", computerForRepair=" + computerForRepair + ", computerSubTypesProblems=" + computerSubTypesProblems
				+ "]";
	}

}
