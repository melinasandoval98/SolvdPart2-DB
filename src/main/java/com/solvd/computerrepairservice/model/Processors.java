package com.solvd.computerrepairservice.model;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlEnum
public enum Processors {
    @XmlEnumValue("1") INTEL_I3(1), @XmlEnumValue("2") INTEL_I5(2), @XmlEnumValue("3") INTEL_I7(3), @XmlEnumValue("4") INTEL_CORE_DUO(4);

    private long processorID;


    private Processors(long processorID) {
        this.processorID = processorID;
    }

    public long getProcessorID() {
        return processorID;
    }

    @Override
    public String toString() {
        return "Processors{" +
                "processorID=" + processorID +
                '}';
    }
}
