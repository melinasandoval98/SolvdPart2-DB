package com.solvd.computerrepairservice.jaxb;

import com.solvd.computerrepairservice.model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;

public class UserJAXBHandler {
    public static final Logger LOGGER = LogManager.getLogger(UserJAXBHandler.class);
    private static File readingFile = new File("src/main/resources/xml/user.xml");
    private static File writingFile = new File("src/main/resources/xml/jaxb/userJAXB.xml");

    private static User unmarshallUser() throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(User.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        User user = (User) unmarshaller.unmarshal(readingFile);
        return user;
    }

    public static User userUnmarshaller() {
        User user = null;
        try {
            user = unmarshallUser();
        } catch (JAXBException e) {
            LOGGER.error(e);
        }
        return user;
    }

    private static void marshallUser(User user) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(User.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        marshaller.marshal(user, writingFile);
    }

    public static void userMarshaller(User user) {
        try {
            marshallUser(user);
        } catch (JAXBException e) {
            LOGGER.error(e);
        }
    }


}
