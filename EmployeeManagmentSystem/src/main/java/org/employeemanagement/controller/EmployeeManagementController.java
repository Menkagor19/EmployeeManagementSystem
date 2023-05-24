package org.employeemanagement.controller;

import org.employeemanagement.exception.AadharAlreadyExistException;
import org.employeemanagement.exception.EmployeeDoesNotExist;
import org.employeemanagement.entity.Employee;
import org.employeemanagement.dto.EmployeeDTO;
import org.employeemanagement.service.EmployeeManagementService;
import org.employeemanagement.utility.ResponseHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class EmployeeManagementController {

    @Autowired
    private EmployeeManagementService employeeManagementService;

    @PostMapping(value = "/employee")
    public ResponseEntity<Object> createEmployee(@RequestBody EmployeeDTO employee) throws AadharAlreadyExistException {
        int employeeId = employeeManagementService.createEmployee(employee);
        String successMessage = "INSERT_SUCCESS with employeeId  " + employeeId;
        return ResponseHandler.generateResponse(successMessage, HttpStatus.CREATED);
    }

    @GetMapping(value = "/employee/{aadhar}")
    public ResponseEntity<Object> getEmployee(@PathVariable String aadhar) {
        try{
            Employee resultingEmployee = (Employee) employeeManagementService.getEmployee(aadhar);
            return new ResponseEntity<>(resultingEmployee,HttpStatus.OK);
        }catch (EmployeeDoesNotExist e){
            String errorMessage = "Employee does not exist";
            return ResponseHandler.generateResponse(errorMessage, HttpStatus.NOT_FOUND);
        }

    }

    @PutMapping(value = "employee/{aadhar}")
    public ResponseEntity<Object> updateEmployee(@PathVariable String aadhar, @RequestBody EmployeeDTO employeeDTO) throws EmployeeDoesNotExist {
        employeeManagementService.updateEmployee(aadhar, employeeDTO.getDept());
        String successMessage = "UPDATE_SUCCESS";
        return ResponseHandler.generateResponse(successMessage, HttpStatus.OK);
    }
}
