package com.example.gestionvisiteurfreelance.Dto;

import lombok.*;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode

public class EmployeeDto {
    private Long personId;

    private String firstName;

    private String lastName;

    private double phoneNumber;

    private LocalDate creationDate;

    private boolean isActive;

    private Long companyId;

    private Long departmentId;


}
