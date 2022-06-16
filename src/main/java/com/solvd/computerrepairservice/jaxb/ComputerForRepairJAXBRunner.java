package com.solvd.computerrepairservice.jaxb;

import com.solvd.computerrepairservice.model.ComputerForRepair;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ComputerForRepairJAXBRunner {
    public static final Logger LOGGER = LogManager.getLogger(ComputerForRepairJAXBRunner.class);

    public static void main(String[] args) {
        ComputerForRepair computerForRepair = ComputerForRepairJAXBHandler.ComputerForRepairUnmarshaller();
        LOGGER.info(computerForRepair);
        ComputerForRepairJAXBHandler.ComputerForRepairMarshaller(computerForRepair);
    }

}
