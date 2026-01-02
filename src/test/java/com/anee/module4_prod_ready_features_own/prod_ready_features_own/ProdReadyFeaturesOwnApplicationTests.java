package com.anee.module4_prod_ready_features_own.prod_ready_features_own;

import com.anee.module4_prod_ready_features_own.prod_ready_features_own.clients.EmployeeClient;
import com.anee.module4_prod_ready_features_own.prod_ready_features_own.dto.EmployeeDTO;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ProdReadyFeaturesOwnApplicationTests {

    @Autowired
    private EmployeeClient employeeClient;

	@Test
    @Order(3)
    void getAllEmployees() {
        List<EmployeeDTO> employeeDTOList = employeeClient.getAllEmployees();
        System.out.println(employeeDTOList);
    }

    @Test
    @Order(2)
    void getEmployeeByIdTest() {
        EmployeeDTO employeeDTO = employeeClient.getEmployeeById(1L);
        System.out.println(employeeDTO);
    }

//    @Test
//    @Order(1)
//    void createEmployeeTest() {
//        EmployeeDTO employeeDTO = new EmployeeDTO(
//                null, "Aneer", "aneer@gmail.com", 26, "USER", 5000.00,
//                LocalDate.of(2020, 12,1), true);
//        EmployeeDTO savedEmployeeDTO = employeeClient.createNewEmployee(employeeDTO);
//        System.out.println(savedEmployeeDTO);
//    }

}
