package com.solvd.computerrepairservice.saxparser;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;

import com.solvd.computerrepairservice.model.User;

public class UserSAXRunner {
	public static final Logger LOGGER = LogManager.getLogger(UserSAXRunner.class);

	private static void xmlSAXParsing(UserXMLSAXParser userHandler) {
		try {
			SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
			SAXParser saxParser = saxParserFactory.newSAXParser();
			String path = System.getProperty("user.dir");
			File file = new File("/Users/solvd/Desktop/Laba-DB/SolvdPart2-DB/src/main/resources/xml/user.xml");
			saxParser.parse(file, userHandler);
		} catch (ParserConfigurationException | SAXException | IOException e) {
			LOGGER.error(e);
		}

	}

	public static void main(String[] args) {
		UserXMLSAXParser userHandler = new UserXMLSAXParser();
		xmlSAXParsing(userHandler);
		ArrayList<User> users = userHandler.getUsers();
		users.stream().forEach(user -> LOGGER.info(user));
	}

}
