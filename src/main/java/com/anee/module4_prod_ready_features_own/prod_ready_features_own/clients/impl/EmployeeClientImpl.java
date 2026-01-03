package com.anee.module4_prod_ready_features_own.prod_ready_features_own.clients.impl;

import com.anee.module4_prod_ready_features_own.prod_ready_features_own.advice.ApiResponse;
import com.anee.module4_prod_ready_features_own.prod_ready_features_own.clients.EmployeeClient;
import com.anee.module4_prod_ready_features_own.prod_ready_features_own.dto.EmployeeDTO;
import com.anee.module4_prod_ready_features_own.prod_ready_features_own.exceptions.ResourceNoteFoundException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeClientImpl implements EmployeeClient {

    private final RestClient restClient;

    Logger log = LoggerFactory.getLogger(EmployeeClientImpl.class);

    @Override
    public List<EmployeeDTO> getAllEmployees() {
        log.trace("Trying to retrieve all employees in getAllEmployees");
        try {
            log.info("Attempting to retrieve all employees in getAllEmployees");
            ApiResponse<List<EmployeeDTO>> emploeeDTOList = restClient.get()
                    .uri("employees")
                    .retrieve()
                    .onStatus(HttpStatusCode::is4xxClientError, (req, res) -> {
                        log.error(new String(res.getBody().readAllBytes()));
                        throw new ResourceNoteFoundException("could not create employee");
                    })
                    .body(new ParameterizedTypeReference<>() {
                    });

            log.debug("Successfully retrieved all employees in getAllEmployees");
            log.trace("Retrieved employee data: {}", emploeeDTOList.getData());
            return emploeeDTOList.getData();
        } catch (Exception e) {
            log.error("Exception occurred in getAllEmployees: ", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public EmployeeDTO getEmployeeById(Long employeeId) {
        log.trace("Trying to retrieve employee with ID: {} in getEmployeeById", employeeId);
        try {
            ApiResponse<EmployeeDTO> employeeResponse = restClient.get()
                    .uri("employees/{employeeId}", employeeId)
                    .retrieve()
                    .onStatus(HttpStatusCode::is4xxClientError, (req, res) -> {
                        log.error(new String(res.getBody().readAllBytes()));
                        throw new ResourceNoteFoundException("could not create employee");
                    })
                    .body(new ParameterizedTypeReference<>() {
                    });

            return employeeResponse.getData();
        } catch (Exception e) {
            log.error("Exception occurred in getEmployeeById for ID {}: ", employeeId, e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public EmployeeDTO createNewEmployee(EmployeeDTO employeeDTO) {
        log.trace("Trying to create Employee with information {}", employeeDTO);
        try {
            ResponseEntity<ApiResponse<EmployeeDTO>> employeeDTOApiResponse = restClient.post()
                    .uri("employees")
                    .body(employeeDTO)
                    .retrieve()
                    .onStatus(HttpStatusCode::is4xxClientError, (req, res) -> {
                        log.debug("4xxClient error during create new employee");
                        log.error(new String(res.getBody().readAllBytes()));
                        throw new ResourceNoteFoundException("could not create employee");
                    })
                    .toEntity(new ParameterizedTypeReference<>() {
                    }); // to get a ResponseEntity with generic type

            log.trace("Successfully created a new Employee {}", employeeDTOApiResponse.getBody());
            return employeeDTOApiResponse.getBody().getData();
        } catch (Exception e) {
            log.error("Exception occurred in createNewEmployee", e);
            throw new RuntimeException(e);
        }
    }
}
