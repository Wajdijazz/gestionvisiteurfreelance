package com.example.gestionvisiteurfreelance.Security;

import javax.transaction.Transactional;

import com.example.gestionvisiteurfreelance.Repository.UserRepository;
import com.example.gestionvisiteurfreelance.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Transactional
@Service
public class UserDetailsServiceImpl  implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) {
        User user = userRepository.findByEmail(email);
        if (user == null)
            throw new ResourceNotFoundException("User  Not Found with -> email " + email);
        return UserPrinciple.build(user);
    }
}
