package com.solvd.computerrepairservice.jaxb;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.solvd.computerrepairservice.model.ComputerForRepair;

public class ComputerForRepairJAXBHandling {
	public static final Logger LOGGER = LogManager.getLogger(ComputerForRepairJAXBHandling.class);

	private static ComputerForRepair jaxbHandler() {
		ComputerForRepair computerForRepair = null;
		try {
			JAXBContext context = JAXBContext.newInstance(ComputerForRepair.class);
			Unmarshaller unmarshaller = context.createUnmarshaller();
			String path = System.getProperty("user.dir");
			computerForRepair = (ComputerForRepair) unmarshaller.unmarshal(new File(
					path + "src\\main\\resources\\xml\\computerforrepair.xml"));

		} catch (JAXBException e) {
			LOGGER.error(e);
		}

		return computerForRepair;

	}

	public static void main(String[] args) {
		LOGGER.info(jaxbHandler());

	}
}
