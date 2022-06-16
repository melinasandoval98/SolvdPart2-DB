package com.solvd.computerrepairservice.model;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "computer")
@XmlType(propOrder = {"computerID", "computerModel", "computerYear", "computerOS", "computerProcessor",
        "computerDataStorageDevice", "computerDataStorageDeviceCapacity"})
public class Computer {
    private long computerID;
    private String computerModel;
    private int computerYear;
    private OperatingSystems computerOS;
    private Processors computerProcessor;
    private DataStorageDeviceTypes computerDataStorageDevice;
    private String computerDataStorageDeviceCapacity;

    public Computer() {

    }

    public Computer(long computerID, String computerModel, int computerYear, OperatingSystems oS,
                    Processors computerProcessor, DataStorageDeviceTypes computerDataStorageDevice,
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

    @XmlAttribute(name = "computerID")
    public void setComputerID(long computerID) {
        this.computerID = computerID;
    }

    public String getComputerModel() {
        return computerModel;
    }

    @XmlElement(name = "computerModel")
    public void setComputerModel(String computerModel) {
        this.computerModel = computerModel;
    }

    public int getComputerYear() {
        return computerYear;
    }

    @XmlElement(name = "computerYear")
    public void setComputerYear(int computerYear) {
        this.computerYear = computerYear;
    }

    public OperatingSystems getComputerOS() {
        return computerOS;
    }

    @XmlElement(name = "computerOS")
    public void setComputerOS(OperatingSystems computerOS) {
        this.computerOS = computerOS;
    }

    public Processors getComputerProcessor() {
        return computerProcessor;
    }

    @XmlElement(name = "computerProcessor")
    public void setComputerProcessor(Processors computerProcessor) {
        this.computerProcessor = computerProcessor;
    }

    public DataStorageDeviceTypes getComputerDataStorageDevice() {
        return computerDataStorageDevice;
    }

    @XmlElement(name = "computerDataStorageDevice")
    public void setComputerDataStorageDevice(DataStorageDeviceTypes computerDataStorageDevice) {
        this.computerDataStorageDevice = computerDataStorageDevice;
    }

    public String getComputerDataStorageDeviceCapacity() {
        return computerDataStorageDeviceCapacity;
    }

    @XmlElement(name = "computerDataStorageDeviceCapacity")
    public void setComputerDataStorageDeviceCapacity(String computerDataStorageDeviceCapacity) {
        this.computerDataStorageDeviceCapacity = computerDataStorageDeviceCapacity;
    }

    @Override
    public String toString() {
        return "Computer{" +
                "computerID=" + computerID +
                ", computerModel='" + computerModel + '\'' +
                ", computerYear=" + computerYear +
                ", computerOS=" + computerOS +
                ", computerProcessor=" + computerProcessor +
                ", computerDataStorageDevice=" + computerDataStorageDevice +
                ", computerDataStorageDeviceCapacity='" + computerDataStorageDeviceCapacity + '\'' +
                '}';
    }
}
