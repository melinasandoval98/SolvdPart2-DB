package com.solvd.computerrepairservice.jackson;

import com.solvd.computerrepairservice.model.Client;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ClientJacksonRunner {
    public static final Logger LOGGER = LogManager.getLogger(ClientJacksonRunner.class);

    public static void main(String[] args) {
        Client client = ClientJacksonHandler.readUserFromJsonFile();
        LOGGER.info(client);
        ClientJacksonHandler.writeUserFromJsonFile(client);
    }
}
