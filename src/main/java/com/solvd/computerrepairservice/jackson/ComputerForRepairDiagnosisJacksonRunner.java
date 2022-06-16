package com.solvd.computerrepairservice.jackson;

import com.solvd.computerrepairservice.model.ComputerForRepairDiagnosis;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ComputerForRepairDiagnosisJacksonRunner {
    public static final Logger LOGGER = LogManager.getLogger(ComputerForRepairDiagnosisJacksonRunner.class);

    public static void main(String[] args) {
        ComputerForRepairDiagnosis computerForRepairDiagnosis = ComputerForRepairDiagnosisJacksonHandler.readUserFromJsonFile();
        LOGGER.info(computerForRepairDiagnosis);
        ComputerForRepairDiagnosisJacksonHandler.writeUserFromJsonFile(computerForRepairDiagnosis);
    }
}
