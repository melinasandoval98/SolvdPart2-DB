package com.solvd.computerrepairservice.model;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "computer")
@XmlType(propOrder = { "computerID", "computerModel", "computerYear", "oS", "computerProcessor",
		"computerDataStorageDevice", "computerDataStorageDeviceCapacity" })
public class Computer {
	private long computerID;
	private String computerModel;
	private int computerYear;
	private OperatingSystems oS;
	private Processors computerProcessor;
	private DataStorageTypes computerDataStorageDevice;
	private String computerDataStorageDeviceCapacity;

	public Computer() {

	}

	public Computer(long computerID, String computerModel, int computerYear, OperatingSystems oS,
			Processors computerProcessor, DataStorageTypes computerDataStorageDevice,
			String computerDataStorageDeviceCapacity) {
		super();
		this.computerID = computerID;
		this.computerModel = computerModel;
		this.computerYear = computerYear;
		this.oS = oS;
		this.computerProcessor = computerProcessor;
		this.computerDataStorageDevice = computerDataStorageDevice;
		this.computerDataStorageDeviceCapacity = computerDataStorageDeviceCapacity;
	}

	@XmlAttribute(name = "computerID")
	public long getComputerID() {
		return computerID;
	}

	public void setComputerID(long computerID) {
		this.computerID = computerID;
	}

	public String getComputerModel() {
		return computerModel;
	}

	public void setComputerModel(String computerModel) {
		this.computerModel = computerModel;
	}

	public int getComputerYear() {
		return computerYear;
	}

	public void setComputerYear(int computerYear) {
		this.computerYear = computerYear;
	}

	public OperatingSystems getoS() {
		return oS;
	}

	public void setoS(OperatingSystems oS) {
		this.oS = oS;
	}

	public Processors getComputerProcessor() {
		return computerProcessor;
	}

	public void setComputerProcessor(Processors computerProcessor) {
		this.computerProcessor = computerProcessor;
	}

	public DataStorageTypes getComputerDataStorageDevice() {
		return computerDataStorageDevice;
	}

	public void setComputerDataStorageDevice(DataStorageTypes computerDataStorageDevice) {
		this.computerDataStorageDevice = computerDataStorageDevice;
	}

	public String getComputerDataStorageDeviceCapacity() {
		return computerDataStorageDeviceCapacity;
	}

	public void setComputerDataStorageDeviceCapacity(String computerDataStorageDeviceCapacity) {
		this.computerDataStorageDeviceCapacity = computerDataStorageDeviceCapacity;
	}

	@Override
	public String toString() {
		return "Computer [computerID=" + computerID + ", computerModel=" + computerModel + ", computerYear="
				+ computerYear + ", oS=" + oS + ", computerProcessor=" + computerProcessor
				+ ", computerDataStorageDevice=" + computerDataStorageDevice + ", computerDataStorageDeviceCapacity="
				+ computerDataStorageDeviceCapacity + "]";
	}

}
