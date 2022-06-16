package com.solvd.computerrepairservice.service.jdbcimpl;

import com.solvd.computerrepairservice.dao.jdbcmysqlimplementation.ComputerForRepairDAO;
import com.solvd.computerrepairservice.dao.jdbcmysqlimplementation.EmployeeDAO;
import com.solvd.computerrepairservice.dao.jdbcmysqlimplementation.GenderDAO;
import com.solvd.computerrepairservice.dao.jdbcmysqlimplementation.UserDAO;
import com.solvd.computerrepairservice.model.Employee;
import com.solvd.computerrepairservice.service.IEmployeeService;

import java.sql.SQLException;
import java.util.List;

public class EmployeeService implements IEmployeeService {
    EmployeeDAO employeeDAO;
    UserDAO userDAO;
    GenderDAO genderDAO;
    ComputerForRepairDAO computerForRepairDAO;

    @Override
    public Employee getFullInformationOfEntityByID(long entityID) throws SQLException {
        Employee employee = employeeDAO.getEntityByID(entityID);
        employee.setUser(userDAO.getUserByEmployeeID(entityID));
        employee.setEmployeeGender(genderDAO.getGenderByEmployeeID(entityID));
        employee.setUserComputersForRepair(computerForRepairDAO.getComputersForRepairByEmployeeID(entityID));
        return employee;
    }

    @Override
    public List<Employee> getFullInformationOfAllEntities() throws SQLException {
        List<Employee> employees = employeeDAO.getAll();
        employees.stream().forEach(employee -> {
            employee.setUser(userDAO.getUserByEmployeeID(employee.getEmployeeID()));
            employee.setEmployeeGender(genderDAO.getGenderByEmployeeID(employee.getEmployeeID()));
            try {
                employee.setUserComputersForRepair(computerForRepairDAO.getComputersForRepairByEmployeeID(employee.getEmployeeID()));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
        return employees;
    }
}
