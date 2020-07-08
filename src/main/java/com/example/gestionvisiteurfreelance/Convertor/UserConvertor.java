package com.example.gestionvisiteurfreelance.Convertor;

import com.example.gestionvisiteurfreelance.Dto.UserDto;
import com.example.gestionvisiteurfreelance.Services.IEmployeeServices;
import com.example.gestionvisiteurfreelance.entities.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.springframework.stereotype.Component;
@Builder
@AllArgsConstructor
@Component
public class UserConvertor implements GenericsConverter<User, UserDto>  {

    private IEmployeeServices employeeServices;

    @Override
    public UserDto entityToDto(User user) {
        // TODO Auto-generated method stub
        return UserDto.builder()
                .email(user.getEmail())
                .password(user.getPassword())
                .personId(user.getPerson().getPersonId())
                .isActive(user.isActive())
                .build();
    }

    @Override
    public User dtoToEntity(UserDto userDto) {
        // TODO Auto-generated method stub
        return User.builder()
                .email(userDto.getEmail())
                .password(userDto.getPassword())
                .person(employeeServices.getPersonById(userDto.getPersonId()))
                .isActive(userDto.isActive())
                .build();

    }
}
