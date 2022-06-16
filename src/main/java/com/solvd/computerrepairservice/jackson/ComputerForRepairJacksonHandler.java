package com.solvd.computerrepairservice.jackson;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.solvd.computerrepairservice.model.ComputerForRepair;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;

public class ComputerForRepairJacksonHandler {
    public static final Logger LOGGER = LogManager.getLogger(ComputerForRepairJacksonHandler.class);
    private File readingFile = new File("src/main/resources/json/Client.json");
    private File writingFile = new File("src/main/resources/json/jackson/JacksonClient.json");

    private static ObjectMapper objectMapper = new ObjectMapper();

    private static ComputerForRepair readUser() throws IOException {
        File readingFile = new File("src/main/resources/json/User.json");
        ComputerForRepair computerForRepair = objectMapper.readValue(readingFile, ComputerForRepair.class);
        return computerForRepair;
    }

    public static ComputerForRepair readUserFromJsonFile() {
        ComputerForRepair computerForRepair = null;
        try {
            computerForRepair = readUser();
        } catch (IOException e) {
            LOGGER.error(e);
        }
        return computerForRepair;
    }

    private static void writeUser(ComputerForRepair computerForRepair) throws IOException {
        File writingFile = new File("src/main/resources/json/jackson/JacksonUser.json");
        objectMapper.writerWithDefaultPrettyPrinter().writeValue(writingFile, computerForRepair);
    }

    public static void writeUserFromJsonFile(ComputerForRepair computerForRepair) {
        try {
            writeUser(computerForRepair);
        } catch (IOException e) {
            LOGGER.error(e);
        }
    }

}
