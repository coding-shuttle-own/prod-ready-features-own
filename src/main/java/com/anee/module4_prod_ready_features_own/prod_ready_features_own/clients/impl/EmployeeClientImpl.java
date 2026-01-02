package com.anee.module4_prod_ready_features_own.prod_ready_features_own.clients.impl;

import com.anee.module4_prod_ready_features_own.prod_ready_features_own.advice.ApiResponse;
import com.anee.module4_prod_ready_features_own.prod_ready_features_own.clients.EmployeeClient;
import com.anee.module4_prod_ready_features_own.prod_ready_features_own.dto.EmployeeDTO;
import com.anee.module4_prod_ready_features_own.prod_ready_features_own.exceptions.ResourceNoteFoundException;
import lombok.RequiredArgsConstructor;
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

    @Override
    public List<EmployeeDTO> getAllEmployees() {
        try {
            ApiResponse<List<EmployeeDTO>> emploeeDTOList = restClient.get()
                    .uri("employees")
                    .retrieve()
                    .body(new ParameterizedTypeReference<>() {
                    });

            return emploeeDTOList.getData();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public EmployeeDTO getEmployeeById(Long employeeId) {
        try {
            ApiResponse<EmployeeDTO> employeeResponse = restClient.get()
                    .uri("employees/{employeeId}", employeeId)
                    .retrieve()
                    .body(new ParameterizedTypeReference<>() {
                    });

            return employeeResponse.getData();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public EmployeeDTO createNewEmployee(EmployeeDTO employeeDTO) {
        try {
            ResponseEntity<ApiResponse<EmployeeDTO>> employeeDTOApiResponse = restClient.post()
                    .uri("employees")
                    .body(employeeDTO)
                    .retrieve()
                    .onStatus(HttpStatusCode::is4xxClientError, (req, res) -> {
                        System.out.println(new String(res.getBody().readAllBytes()));
                        throw new ResourceNoteFoundException("could not create employee");
                    })
                    .toEntity(new ParameterizedTypeReference<>() {
                    }); // to get a ResponseEntity with generic type

            return employeeDTOApiResponse.getBody().getData();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
