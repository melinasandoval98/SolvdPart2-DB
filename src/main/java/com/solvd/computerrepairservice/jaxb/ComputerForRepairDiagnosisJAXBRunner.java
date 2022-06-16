package com.solvd.computerrepairservice.jaxb;

import com.solvd.computerrepairservice.model.ComputerForRepairDiagnosis;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class ComputerForRepairDiagnosisJAXBRunner {
    public static final Logger LOGGER = LogManager.getLogger(ComputerForRepairDiagnosisJAXBRunner.class);

    public static void main(String[] args) {
        List<ComputerForRepairDiagnosis> computerForRepairDiagnosis = ComputerForRepairDiagnosisJAXBHandler.computerForRepairDiagnosisUnmarshaller();
        LOGGER.info(computerForRepairDiagnosis);
        computerForRepairDiagnosis.forEach(computerFRD -> ComputerForRepairDiagnosisJAXBHandler.computerForRepairDiagnosisMarshaller(computerFRD));
    }

}
