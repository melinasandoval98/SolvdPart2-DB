package com.solvd.computerrepairservice.runner;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.solvd.computerrepairservice.dao.IComputerDAO;
import com.solvd.computerrepairservice.dao.IPhoneNumberDAO;
import com.solvd.computerrepairservice.jdbcmysqlimplementation.AddressDAO;
import com.solvd.computerrepairservice.jdbcmysqlimplementation.ComputerDAO;
import com.solvd.computerrepairservice.jdbcmysqlimplementation.ComputerForRepairDAO;
import com.solvd.computerrepairservice.jdbcmysqlimplementation.DataStorageDeviceTypeDAO;
import com.solvd.computerrepairservice.jdbcmysqlimplementation.GenderDAO;
import com.solvd.computerrepairservice.jdbcmysqlimplementation.OperatingSystemDAO;
import com.solvd.computerrepairservice.jdbcmysqlimplementation.PhoneNumberDAO;
import com.solvd.computerrepairservice.jdbcmysqlimplementation.ProcessorDAO;
import com.solvd.computerrepairservice.jdbcmysqlimplementation.UserDAO;
import com.solvd.computerrepairservice.model.Computer;

public class DAORunner {
	public static final Logger LOGGER = LogManager.getLogger(DAORunner.class);

	public static void main(String[] args) throws SQLException, ClassNotFoundException {
		Connection conn = null;

		try {
			conn = DriverManager.getConnection("jdbc:mysql://52.59.193.212:3306/Melina_Sandoval?user=root", "root",
					"devintern");
			PhoneNumberDAO phoneNumberDAO = new PhoneNumberDAO(conn);
			AddressDAO adressDAO = new AddressDAO(conn);
			GenderDAO genderDAO = new GenderDAO(conn);
			UserDAO userDAO = new UserDAO(conn);
			ComputerDAO computerDAO = new ComputerDAO(conn);
			OperatingSystemDAO oSDAO = new OperatingSystemDAO(conn);
			ProcessorDAO processorDAO = new ProcessorDAO(conn);
			DataStorageDeviceTypeDAO dataStorageDeviceType = new DataStorageDeviceTypeDAO(conn);
			ComputerForRepairDAO computerForRepairDAO = new ComputerForRepairDAO(conn);

		//	userDAO.setComputerForRepairDAO(computerForRepairDAO);
		//	userDAO.setUserGenderDAO(genderDAO);
		//	userDAO.setUserPhoneNumberDAO(phoneNumberDAO);
		//	userDAO.setUserAdressDAO(adressDAO);
		//	computerDAO.setDataStorageTypeDAO(dataStorageDeviceType);
		//	computerDAO.setOperatingSystemDAO(oSDAO);
		//	computerDAO.setProcessorDAO(processorDAO);
		//	computerForRepairDAO.setComputerDAO(computerDAO);
		//	computerForRepairDAO.setEmployeeDAO(userDAO);
		//	computerForRepairDAO.setUserDAO(userDAO);

			IPhoneNumberDAO iPhoneNumberDAO = phoneNumberDAO;
//			IAdressDAO iAdressDAO = adressDAO;
//			IGenderDAO iGenderDAO = genderDAO;
//			IUserDAO iUserDAO = userDAO;
			IComputerDAO iComputerDAO = computerDAO;
//			IOperatingSystemDAO iOSDAO = oSDAO;
//			IProcessorDAO iProcessorDAO = processorDAO;
//			IDataStorageTypeDAO iDataStorageDeviceType = dataStorageDeviceType;
//			IComputerForRepairDAO iComputerForRepairDAO = computerForRepairDAO;

//			iUserDAO.getAll().stream().forEach(user -> LOGGER.info(user));

			LOGGER.info(iPhoneNumberDAO.getEntityByID(3));

			for (Computer computer : iComputerDAO.getAll()) {
				LOGGER.info(computer);
			}

			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				LOGGER.error(e);
			}

		} finally {
			conn.close();
		}

	}

}
