package com.solvd.computerrepairservice.jackson;

import com.solvd.computerrepairservice.model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class UserJacksonRunner {
    public static final Logger LOGGER = LogManager.getLogger(UserJacksonRunner.class);

    public static void main(String[] args) {
        User user = UserJacksonHandler.readUserFromJsonFile();
        LOGGER.info(user);
        UserJacksonHandler.writeUserFromJsonFile(user);
    }
}
