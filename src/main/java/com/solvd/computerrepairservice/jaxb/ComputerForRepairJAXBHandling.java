package com.solvd.computerrepairservice.jaxb;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ComputerForRepairJAXBHandling {
	public static final Logger LOGGER = LogManager.getLogger(ComputerForRepairJAXBHandling.class);

	private static ComputerForRepair jaxbHandler() {
		ComputerForRepair computersForRepair = null;
		try {
			JAXBContext context = JAXBContext.newInstance(ComputerForRepair.class);
			Unmarshaller unmarshaller = context.createUnmarshaller();
			String path = System.getProperty("user.dir");
			computersForRepair = (ComputerForRepair) unmarshaller
					.unmarshal(new File(path + "/src/main/resources/computerforrepair.xml"));
		} catch (JAXBException e) {
			LOGGER.error(e);
		}

		return computersForRepair;

	}

	public static void main(String[] args) {
		jaxbHandler().toString();
		jaxbHandler().getComputerRepairers().stream().forEach(computerRepairer -> LOGGER.info(computerRepairer));

	}
}
