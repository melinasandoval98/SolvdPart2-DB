package com.solvd.computerrepairservice.jackson;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.solvd.computerrepairservice.model.ComputerForRepairDiagnosis;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;

public class ComputerForRepairDiagnosisJacksonHandler {
    public static final Logger LOGGER = LogManager.getLogger(ComputerForRepairDiagnosisJacksonHandler.class);
    private File readingFile = new File("src/main/resources/json/Client.json");
    private File writingFile = new File("src/main/resources/json/jackson/JacksonClient.json");

    private static ObjectMapper objectMapper = new ObjectMapper();

    private static ComputerForRepairDiagnosis readUser() throws IOException {
        File readingFile = new File("src/main/resources/json/User.json");
        ComputerForRepairDiagnosis computerForRepairDiagnosis = objectMapper.readValue(readingFile, ComputerForRepairDiagnosis.class);
        return computerForRepairDiagnosis;
    }

    public static ComputerForRepairDiagnosis readUserFromJsonFile() {
        ComputerForRepairDiagnosis computerForRepairDiagnosis = null;
        try {
            computerForRepairDiagnosis = readUser();
        } catch (IOException e) {
            LOGGER.error(e);
        }
        return computerForRepairDiagnosis;
    }

    private static void writeUser(ComputerForRepairDiagnosis computerForRepairDiagnosis) throws IOException {
        File writingFile = new File("src/main/resources/json/jackson/JacksonUser.json");
        objectMapper.writerWithDefaultPrettyPrinter().writeValue(writingFile, computerForRepairDiagnosis);
    }

    public static void writeUserFromJsonFile(ComputerForRepairDiagnosis computerForRepairDiagnosis) {
        try {
            writeUser(computerForRepairDiagnosis);
        } catch (IOException e) {
            LOGGER.error(e);
        }
    }

}
