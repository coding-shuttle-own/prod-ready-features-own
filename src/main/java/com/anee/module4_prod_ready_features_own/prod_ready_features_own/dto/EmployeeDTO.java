package com.anee.module4_prod_ready_features_own.prod_ready_features_own.dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class EmployeeDTO {

    private Long id;

    private String name;

    private String email;

    private Integer age;

    private String role; //ADMIN, USER

    private Double salary;

    private LocalDate dateOfJoining;

    private Boolean isActive;
}
