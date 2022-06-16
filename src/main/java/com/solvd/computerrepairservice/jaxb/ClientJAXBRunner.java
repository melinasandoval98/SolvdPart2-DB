package com.solvd.computerrepairservice.jaxb;

import com.solvd.computerrepairservice.model.Client;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ClientJAXBRunner {
    public static final Logger LOGGER = LogManager.getLogger(ClientJAXBRunner.class);

    public static void main(String[] args) {
        Client client = ClientJAXBHandler.clientUnmarshaller();
        LOGGER.info(client);
        ClientJAXBHandler.ClientMarshaller(client);
    }
}
