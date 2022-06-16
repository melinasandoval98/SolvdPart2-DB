package com.solvd.computerrepairservice.jaxb;

import com.solvd.computerrepairservice.model.ComputerForRepairDiagnosis;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ComputerForRepairDiagnosisJAXBHandler {
    public static final Logger LOGGER = LogManager.getLogger(ComputerForRepairJAXBHandler.class);
    private static File readingFile = new File("src/main/resources/xml/ComputerForRepairDiagnosis.xml");
    private static File writingFile = new File("src/main/resources/xml/jaxb/ComputerForRepairDiagnosisJAXB.xml");


    private static ComputerForRepairDiagnosis unmarshallComputerForRepairDiagnosis() throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(ComputerForRepairDiagnosis.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        ComputerForRepairDiagnosis computerForRepairDiagnosis = (ComputerForRepairDiagnosis) unmarshaller.unmarshal(readingFile);
        return computerForRepairDiagnosis;
    }

    public static List<ComputerForRepairDiagnosis> computerForRepairDiagnosisUnmarshaller() {
        List<ComputerForRepairDiagnosis> computerForRepairDiagnosis = new ArrayList<>();
        try {
            computerForRepairDiagnosis.add(unmarshallComputerForRepairDiagnosis());
        } catch (JAXBException e) {
            LOGGER.error(e);
        }
        return computerForRepairDiagnosis;
    }

    private static void marshallComputerForRepairDiagnosis(ComputerForRepairDiagnosis computerForRepairDiagnosis) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(ComputerForRepairDiagnosis.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        marshaller.marshal(computerForRepairDiagnosis, writingFile);
    }

    public static void computerForRepairDiagnosisMarshaller(ComputerForRepairDiagnosis computerForRepairDiagnosis) {
        try {
            marshallComputerForRepairDiagnosis(computerForRepairDiagnosis);
        } catch (JAXBException e) {
            LOGGER.error(e);
        }
    }

}
