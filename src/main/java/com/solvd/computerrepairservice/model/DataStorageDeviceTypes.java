package com.solvd.computerrepairservice.model;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType
@XmlEnum(Integer.class)
public enum DataStorageDeviceTypes {
    @XmlEnumValue("1") HDD(1), @XmlEnumValue("2") SDD(2);

    private long dataStorageTypeID;


    DataStorageDeviceTypes(long dataStorageTypeID) {
        this.dataStorageTypeID = dataStorageTypeID;
    }

    public long getDataStorageTypeID() {
        return dataStorageTypeID;
    }

    @Override
    public String toString() {
        return "DataStorageDeviceTypes{" +
                "dataStorageTypeID=" + dataStorageTypeID +
                '}';
    }
}
