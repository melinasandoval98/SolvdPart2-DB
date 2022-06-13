package com.solvd.computerrepairservice.model;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "computer")
@XmlType(propOrder = {"computerID", "computerModel", "computerYear", "oS", "computerProcessor",
        "computerDataStorageDevice", "computerDataStorageDeviceCapacity"})
public class Computer {
    private long computerID;
    private String computerModel;
    private int computerYear;
    private OperatingSystems computerOS;
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
        this.computerOS = oS;
        this.computerProcessor = computerProcessor;
        this.computerDataStorageDevice = computerDataStorageDevice;
        this.computerDataStorageDeviceCapacity = computerDataStorageDeviceCapacity;
    }

    public Computer(long computerID, String computerModel, int computerYear, String computerDataStorageDeviceCapacity) {
        super();
        this.computerID = computerID;
        this.computerModel = computerModel;
        this.computerYear = computerYear;
        this.computerDataStorageDeviceCapacity = computerDataStorageDeviceCapacity;
    }

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

    public OperatingSystems getComputerOS() {
        return computerOS;
    }

    public void setComputerOS(OperatingSystems computerOS) {
        this.computerOS = computerOS;
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
}
