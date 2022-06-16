package com.solvd.computerrepairservice.jackson;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.solvd.computerrepairservice.model.Computer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;

public class ComputerJacksonHandler {
    public static final Logger LOGGER = LogManager.getLogger(ComputerJacksonHandler.class);
    private File readingFile = new File("src/main/resources/json/Client.json");
    private File writingFile = new File("src/main/resources/json/jackson/JacksonClient.json");

    private static ObjectMapper objectMapper = new ObjectMapper();

    private static Computer readUser() throws IOException {
        File readingFile = new File("src/main/resources/json/User.json");
        Computer computer = objectMapper.readValue(readingFile, Computer.class);
        return computer;
    }

    public static Computer readUserFromJsonFile() {
        Computer computer = null;
        try {
            computer = readUser();
        } catch (IOException e) {
            LOGGER.error(e);
        }
        return computer;
    }

    private static void writeUser(Computer computer) throws IOException {
        File writingFile = new File("src/main/resources/json/jackson/JacksonUser.json");
        objectMapper.writerWithDefaultPrettyPrinter().writeValue(writingFile, computer);
    }

    public static void writeUserFromJsonFile(Computer computer) {
        try {
            writeUser(computer);
        } catch (IOException e) {
            LOGGER.error(e);
        }
    }
}
