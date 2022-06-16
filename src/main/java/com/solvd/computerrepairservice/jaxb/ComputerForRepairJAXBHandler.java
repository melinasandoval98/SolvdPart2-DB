package com.solvd.computerrepairservice.jaxb;

import com.solvd.computerrepairservice.model.ComputerForRepair;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;

public class ComputerForRepairJAXBHandler {
    public static final Logger LOGGER = LogManager.getLogger(ComputerForRepairJAXBHandler.class);
    private static File readingFile = new File("src/main/resources/xml/ComputerForRepair.xml");
    private static File writingFile = new File("src/main/resources/xml/jaxb/ComputerForRepairJAXB.xml");

    private static ComputerForRepair unmarshallComputerForRepair() throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(ComputerForRepair.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        ComputerForRepair computerForRepair = (ComputerForRepair) unmarshaller.unmarshal(readingFile);
        return computerForRepair;
    }

    public static ComputerForRepair ComputerForRepairUnmarshaller() {
        ComputerForRepair computerForRepair = null;
        try {
            computerForRepair = unmarshallComputerForRepair();
        } catch (JAXBException e) {
            LOGGER.error(e);
        }
        return computerForRepair;
    }

    private static void marshallComputerForRepair(ComputerForRepair computerForRepair) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(ComputerForRepair.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        marshaller.marshal(computerForRepair, writingFile);
    }

    public static void ComputerForRepairMarshaller(ComputerForRepair computerForRepair) {
        try {
            marshallComputerForRepair(computerForRepair);
        } catch (JAXBException e) {
            LOGGER.error(e);
        }
    }

}
