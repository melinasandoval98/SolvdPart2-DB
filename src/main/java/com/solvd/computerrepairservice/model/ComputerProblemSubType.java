package com.solvd.computerrepairservice.model;

import java.util.ArrayList;
import java.util.List;

public class ComputerProblemSubType {
    private long id;
    private ComputerProblemTypes problemType;
    private String problemDescription;
    private Long serviceSubPrice;
    private List<ComputerForRepairDiagnosis> computerForRepairDiagnosis = new ArrayList<>();

    public ComputerProblemSubType() {
    }

    public ComputerProblemSubType(long id, ComputerProblemTypes problemType, String problemDescription, Long serviceSubPrice, List<ComputerForRepairDiagnosis> computerForRepairDiagnosis) {
        this.id = id;
        this.problemType = problemType;
        this.problemDescription = problemDescription;
        this.serviceSubPrice = serviceSubPrice;
    }

    public ComputerProblemSubType(long id, String problemDescription, Long serviceSubPrice) {
        this.id = id;
        this.problemDescription = problemDescription;
        this.serviceSubPrice = serviceSubPrice;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public ComputerProblemTypes getProblemType() {
        return problemType;
    }

    public void setProblemType(ComputerProblemTypes problemType) {
        this.problemType = problemType;
    }

    public String getProblemDescription() {
        return problemDescription;
    }

    public void setProblemDescription(String problemDescription) {
        this.problemDescription = problemDescription;
    }

    public Long getServiceSubPrice() {
        return serviceSubPrice;
    }

    public void setServiceSubPrice(Long serviceSubPrice) {
        this.serviceSubPrice = serviceSubPrice;
    }

    public List<ComputerForRepairDiagnosis> getComputerForRepairDiagnosis() {
        return computerForRepairDiagnosis;
    }

    public void setComputerForRepairDiagnosis(List<ComputerForRepairDiagnosis> computerForRepairDiagnosis) {
        this.computerForRepairDiagnosis = computerForRepairDiagnosis;
    }
}
