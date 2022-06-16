package com.solvd.computerrepairservice.jaxb;

import com.solvd.computerrepairservice.model.Computer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ComputerJAXBRunner {
    public static final Logger LOGGER = LogManager.getLogger(ComputerJAXBRunner.class);

    public static void main(String[] args) {
        Computer computer = ComputerJAXBHandler.ComputerUnmarshaller();
        LOGGER.info(computer);
        ComputerJAXBHandler.ComputerMarshaller(computer);
    }
}
