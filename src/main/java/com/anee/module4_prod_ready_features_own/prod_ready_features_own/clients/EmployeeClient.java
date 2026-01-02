package com.anee.module4_prod_ready_features_own.prod_ready_features_own.clients;

import com.anee.module4_prod_ready_features_own.prod_ready_features_own.dto.EmployeeDTO;

import java.util.List;

public interface EmployeeClient {

    List<EmployeeDTO> getAllEmployees();

    EmployeeDTO getEmployeeById(Long employeeId);

    EmployeeDTO createNewEmployee(EmployeeDTO employeeDTO);
}
