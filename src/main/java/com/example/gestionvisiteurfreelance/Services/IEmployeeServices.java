package com.example.gestionvisiteurfreelance.Services;

import com.example.gestionvisiteurfreelance.Dto.EmployeeDto;
import com.example.gestionvisiteurfreelance.entities.Employee;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public interface IEmployeeServices {
    Employee getPersonById(Long personId);

    EmployeeDto createPerson (EmployeeDto personDto);

    List<Employee> getPersons(Long companyId);

    void deletePerson(Long personId);

    Employee updatePerson(EmployeeDto personDto);
}
