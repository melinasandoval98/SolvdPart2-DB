package com.solvd.computerrepairservice.model;

public enum OperatingSystems {
    WINDOWS_7(1), WINDOWS_8(2), WINDOWS_10(3), WINDOWS_11(4), LINUX_FEDORA_29(5), LINUX_FEDORA_32(6),
    LINUX_FEDORA_33(7), LINUX_FEDORA_34(8), LINUX_MINT_20(9), LINUX_MINT_21(10), LINUX_UBUNTU_20(11),
    LINUX_UBUNTU_19(12), MACOS_MONTERREY_12(13), MACOS_MOJAVE_10(14);

    private final long O_S_ID;

    OperatingSystems(long oSID) {
        this.O_S_ID = oSID;
    }


    public long getOSID() {
        return O_S_ID;
    }

}
