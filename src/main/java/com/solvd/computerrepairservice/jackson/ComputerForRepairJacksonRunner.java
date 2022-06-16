package com.solvd.computerrepairservice.jackson;

import com.solvd.computerrepairservice.model.ComputerForRepair;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ComputerForRepairJacksonRunner {
    public static final Logger LOGGER = LogManager.getLogger(ComputerForRepairJacksonRunner.class);

    public static void main(String[] args) {
        ComputerForRepair computerForRepair = ComputerForRepairJacksonHandler.readUserFromJsonFile();
        LOGGER.info(computerForRepair);
        ComputerForRepairJacksonHandler.writeUserFromJsonFile(computerForRepair);
    }
}
