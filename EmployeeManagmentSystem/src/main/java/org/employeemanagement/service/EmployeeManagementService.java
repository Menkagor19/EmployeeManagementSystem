package org.employeemanagement.service;

import org.employeemanagement.exception.AadharAlreadyExistException;
import org.employeemanagement.exception.EmployeeDoesNotExist;
import org.employeemanagement.entity.Employee;
import org.employeemanagement.dto.EmployeeDTO;
import org.employeemanagement.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;

@Service
public class EmployeeManagementService {

    public final String AADHAR_ALREADY_EXIST = "Aadhar number is already exist";
    public final String EMPLOYEE_DOES_NOT_EXIST = "Employee does not exist with given aadhar";
    @Autowired
    private final EmployeeRepository employeeRepository;

    public EmployeeManagementService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public int createEmployee(EmployeeDTO employeeDTO) throws AadharAlreadyExistException {

        Employee employeeByAadhar = employeeRepository.findByAadhar(employeeDTO.getAadhar());
        if (employeeByAadhar != null) {
            throw new AadharAlreadyExistException(AADHAR_ALREADY_EXIST);
        }

        LocalDate curDate = LocalDate.now();
        int age = 0;
        if (employeeDTO.getDob() != null) {
            age = Period.between(employeeDTO.getDob(), curDate).getYears();
        }

        Employee employee = new Employee(employeeDTO.getName(), employeeDTO.getAadhar(),
                age, employeeDTO.getDept(), employeeDTO.getCity(), employeeDTO.getDob());
        employeeRepository.save(employee);

        return employee.getId();
    }

    public Employee getEmployee(String aadhar) throws EmployeeDoesNotExist {
        Employee employeeByAadhar = employeeRepository.findByAadhar(aadhar);
        if (employeeByAadhar != null) {
            return employeeByAadhar;
        }
        throw new EmployeeDoesNotExist(EMPLOYEE_DOES_NOT_EXIST);
    }

    public void updateEmployee(String aadhar, String dept) throws EmployeeDoesNotExist {
        Employee employeeByAadhar = employeeRepository.findByAadhar(aadhar);
        if (employeeByAadhar != null) {
            employeeByAadhar.setDept(dept);
        } else
            throw new EmployeeDoesNotExist(EMPLOYEE_DOES_NOT_EXIST);
    }
}
