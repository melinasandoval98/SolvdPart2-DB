package com.solvd.computerrepairservice.jackson;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.solvd.computerrepairservice.model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;

public class UserJacksonHandler {
    public static final Logger LOGGER = LogManager.getLogger(UserJacksonHandler.class);
    private File readingFile = new File("src/main/resources/json/User.json");
    private File writingFile = new File("src/main/resources/json/jackson/JacksonUser.json");

    private static ObjectMapper objectMapper = new ObjectMapper();

    private static User readUser() throws IOException {
        File readingFile = new File("src/main/resources/json/User.json");
        User user = objectMapper.readValue(readingFile, User.class);
        return user;
    }

    public static User readUserFromJsonFile() {
        User user = null;
        try {
            user = readUser();
        } catch (IOException e) {
            LOGGER.error(e);
        }
        return user;
    }

    private static void writeUser(User user) throws IOException {
        File writingFile = new File("src/main/resources/json/jackson/JacksonUser.json");
        objectMapper.writerWithDefaultPrettyPrinter().writeValue(writingFile, user);
    }

    public static void writeUserFromJsonFile(User user) {
        try {
            writeUser(user);
        } catch (IOException e) {
            LOGGER.error(e);
        }
    }

}
