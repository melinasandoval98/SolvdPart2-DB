package com.solvd.computerrepairservice.model;

public enum Processors {
    INTEL_I3(1), INTEL_I5(2), INTEL_I7(3), INTEL_CORE_DUO(4);

    private long processorID;

    private Processors(long processorID) {
        this.processorID = processorID;
    }

    public long getProcessorID() {
        return processorID;
    }

}
