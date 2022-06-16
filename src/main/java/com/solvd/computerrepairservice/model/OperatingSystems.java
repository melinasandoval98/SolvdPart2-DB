package com.solvd.computerrepairservice.model;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType
@XmlEnum(Integer.class)
public enum OperatingSystems {
    @XmlEnumValue("1") WINDOWS_7(1), @XmlEnumValue("2") WINDOWS_8(2), @XmlEnumValue("3") WINDOWS_10(3), @XmlEnumValue("4") WINDOWS_11(4), @XmlEnumValue("5") LINUX_FEDORA_29(5), @XmlEnumValue("6") LINUX_FEDORA_32(6),
    @XmlEnumValue("7") LINUX_FEDORA_33(7), @XmlEnumValue("8") LINUX_FEDORA_34(8), @XmlEnumValue("9") LINUX_MINT_20(9), @XmlEnumValue("10") LINUX_MINT_21(10), @XmlEnumValue("11") LINUX_UBUNTU_20(11),
    @XmlEnumValue("12") LINUX_UBUNTU_19(12), @XmlEnumValue("13") MACOS_MONTERREY_12(13), @XmlEnumValue("14") MACOS_MOJAVE_10(15);

    private long oSID;

    OperatingSystems() {
    }

    OperatingSystems(long oSID) {
        this.oSID = oSID;
    }


    public long getoSID() {
        return oSID;
    }

    @Override
    public String toString() {
        return "OperatingSystems{" +
                "O_S_ID=" + oSID +
                '}';
    }
}
