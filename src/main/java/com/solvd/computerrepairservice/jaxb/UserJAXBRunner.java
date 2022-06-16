package com.solvd.computerrepairservice.jaxb;

import com.solvd.computerrepairservice.model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UserJAXBRunner {
    public static final Logger LOGGER = LogManager.getLogger(UserJAXBRunner.class);

    public static void main(String[] args) {
        User user = UserJAXBHandler.userUnmarshaller();
        LOGGER.info(user);
        UserJAXBHandler.userMarshaller(user);
    }
    
}
