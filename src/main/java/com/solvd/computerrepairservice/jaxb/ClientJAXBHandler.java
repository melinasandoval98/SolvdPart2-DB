package com.solvd.computerrepairservice.jaxb;

import com.solvd.computerrepairservice.model.Client;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;

public class ClientJAXBHandler {
    public static final Logger LOGGER = LogManager.getLogger(ClientJAXBHandler.class);
    private static File readingFile = new File("src/main/resources/xml/Client.xml");
    private static File writingFile = new File("src/main/resources/xml/jaxb/ClientJAXB.xml");

    private static Client unmarshallClient() throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(Client.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        Client client = (Client) unmarshaller.unmarshal(readingFile);
        return client;
    }

    public static Client clientUnmarshaller() {
        Client client = null;
        try {
            client = unmarshallClient();
        } catch (JAXBException e) {
            LOGGER.error(e);
        }
        return client;
    }

    private static void marshallClient(Client client) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(Client.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        marshaller.marshal(client, writingFile);
    }

    public static void ClientMarshaller(Client client) {
        try {
            marshallClient(client);
        } catch (JAXBException e) {
            LOGGER.error(e);
        }
    }

}
