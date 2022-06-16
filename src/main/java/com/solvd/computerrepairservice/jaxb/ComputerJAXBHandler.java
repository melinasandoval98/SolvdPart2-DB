package com.solvd.computerrepairservice.jaxb;

import com.solvd.computerrepairservice.model.Computer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;

public class ComputerJAXBHandler {
    public static final Logger LOGGER = LogManager.getLogger(ComputerJAXBRunner.class);
    private static File readingFile = new File("src/main/resources/xml/Computer.xml");
    private static File writingFile = new File("src/main/resources/xml/jaxb/ComputerJAXB.xml");

    private static Computer unmarshallComputer() throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(Computer.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        Computer computer = (Computer) unmarshaller.unmarshal(readingFile);
        return computer;
    }

    public static Computer ComputerUnmarshaller() {
        Computer computer = null;
        try {
            computer = unmarshallComputer();
        } catch (JAXBException e) {
            LOGGER.error(e);
        }
        return computer;
    }

    private static void marshallComputer(Computer computer) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(Computer.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        marshaller.marshal(computer, writingFile);
    }

    public static void ComputerMarshaller(Computer computer) {
        try {
            marshallComputer(computer);
        } catch (JAXBException e) {
            LOGGER.error(e);
        }
    }

}
