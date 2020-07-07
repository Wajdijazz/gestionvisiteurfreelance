package com.example.gestionvisiteurfreelance.Services;

import com.example.gestionvisiteurfreelance.Dto.UserDetailDto;
import com.example.gestionvisiteurfreelance.Dto.UserDto;
import com.example.gestionvisiteurfreelance.entities.Role;
import com.example.gestionvisiteurfreelance.entities.User;

import javax.mail.MessagingException;
import java.util.Set;

public interface IUserService {
    User createUser(UserDto userDto) throws MessagingException;

    User getUserById(Long userId);

    Role saveRole(Role role);

    Set<Role> affectRoleToUser(UserDto userDto);

    void sendMail(String messageToSend, String email, String password) throws MessagingException;

    User updatePasswordUser(UserDto userDto);

    UserDetailDto getUserInfoByEmail(String email);
}
