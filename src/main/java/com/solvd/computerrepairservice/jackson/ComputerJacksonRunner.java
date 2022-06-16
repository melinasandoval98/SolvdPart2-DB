package com.solvd.computerrepairservice.jackson;

import com.solvd.computerrepairservice.model.Computer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ComputerJacksonRunner {
    public static final Logger LOGGER = LogManager.getLogger(ComputerJacksonRunner.class);

    public static void main(String[] args) {
        Computer computer = ComputerJacksonHandler.readUserFromJsonFile();
        LOGGER.info(computer);
        ComputerJacksonHandler.writeUserFromJsonFile(computer);
    }
}
