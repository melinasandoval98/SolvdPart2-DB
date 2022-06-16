package com.solvd.computerrepairservice.jackson;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.solvd.computerrepairservice.model.Client;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;

public class ClientJacksonHandler {
    public static final Logger LOGGER = LogManager.getLogger(ClientJacksonHandler.class);
    private File readingFile = new File("src/main/resources/json/Client.json");
    private File writingFile = new File("src/main/resources/json/jackson/JacksonClient.json");

    private static ObjectMapper objectMapper = new ObjectMapper();

    private static Client readUser() throws IOException {
        File readingFile = new File("src/main/resources/json/User.json");
        Client client = objectMapper.readValue(readingFile, Client.class);
        return client;
    }

    public static Client readUserFromJsonFile() {
        Client client = null;
        try {
            client = readUser();
        } catch (IOException e) {
            LOGGER.error(e);
        }
        return client;
    }

    private static void writeUser(Client client) throws IOException {
        File writingFile = new File("src/main/resources/json/jackson/JacksonUser.json");
        objectMapper.writerWithDefaultPrettyPrinter().writeValue(writingFile, client);
    }

    public static void writeUserFromJsonFile(Client client) {
        try {
            writeUser(client);
        } catch (IOException e) {
            LOGGER.error(e);
        }
    }

}
