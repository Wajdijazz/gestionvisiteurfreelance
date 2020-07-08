package com.example.gestionvisiteurfreelance.Services.Implementation;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.transaction.Transactional;

import com.example.gestionvisiteurfreelance.Dto.UserDetailDto;
import com.example.gestionvisiteurfreelance.Dto.UserDto;
import com.example.gestionvisiteurfreelance.Repository.RoleRepository;
import com.example.gestionvisiteurfreelance.Repository.UserRepository;
import com.example.gestionvisiteurfreelance.Services.IEmployeeServices;
import com.example.gestionvisiteurfreelance.Services.IUserService;
import com.example.gestionvisiteurfreelance.entities.Role;
import com.example.gestionvisiteurfreelance.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContextException;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Transactional
@Service
public class UserServiceImpl implements IUserService {


    private UserRepository userRepository;

    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

    private IEmployeeServices employeeServices;


    @Qualifier("getJavaMailSendeGmail")
    public JavaMailSender emailSendergmail;

    @Qualifier("getJavaMailSenderYahoo")
    public JavaMailSender emailSenderYahoo;

    private static final String jwtSecret = "JWTSecretKey";

    private static final int jwtExpiration = 86400;

    // create Admin methode to save company and user and person in same time

    @Override
    public User createUser(UserDto userDto) throws MessagingException {
        if (userRepository.findByEmail(userDto.getEmail()) != null)
            throw new ResourceNotFoundException("User already exists");
        if (!userDto.getPassword().equals(userDto.getConfirmedPassword()))
            throw new ResourceNotFoundException("Please confirm your password");
        User userToSave = User.builder().email(userDto.getEmail())
                .password(passwordEncoder.encode(userDto.getPassword()))
                .person(employeeServices.getPersonById(userDto.getPersonId()))
                .build();
        userToSave.setRoles(affectRoleToUser(userDto));
        //	sendMail("", userDto.getEmail(), userDto.getPassword());
        return userRepository.save(userToSave);
    }

    @Override
    public User getUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new ApplicationContextException("This user with Id" + userId + "not exist"));
    }



    @Override
    public void sendMail(String messageToSend, String email, String password) throws MessagingException {
        String tokenGenerated = Jwts.builder().setSubject(email).setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + jwtExpiration * 1000))
                .signWith(SignatureAlgorithm.HS512, jwtSecret).compact();
        MimeMessage messageGmail = emailSendergmail.createMimeMessage();
        MimeMessage messageYahoo = emailSenderYahoo.createMimeMessage();

        boolean multipart = true;
        MimeMessageHelper helperGmail = new MimeMessageHelper(messageGmail, multipart, "utf-8");
        MimeMessageHelper helperYahoo = new MimeMessageHelper(messageYahoo, multipart, "utf-8");

        String htmlMsg = "<h3>Welcome to be a part of our clients</h3>" + "<p>To to authenticate :  </p>"
                + "<p>your login : </p>" + email + "<p>your password : </p>" + password
                + "<p> To change your password click here : " + "http://localhost:4200/reset/" + tokenGenerated;

        messageGmail.setContent(htmlMsg, "text/html");
        helperGmail.setTo(email);
        helperGmail.setSubject("Confirm registration");
        this.emailSendergmail.send(messageGmail);

        messageYahoo.setContent(htmlMsg, "text/html");
        helperYahoo.setTo(email);
        helperYahoo.setSubject("Confirm registration");
        this.emailSenderYahoo.send(messageYahoo);
    }

    @Override
    public Role saveRole(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public Set<Role> affectRoleToUser(UserDto userDto) {
        Set<String> strRoles = userDto.getRole();
        Set<Role> roles = new HashSet<>();
        strRoles.forEach(role -> {
            switch (role) {
                case "SUPERADMIN":
                    Role SuperAdminRole = roleRepository.findByRoleDescription("SUPERADMIN")
                            .orElseThrow(() -> new ApplicationContextException("Fail! -> Cause: User Role not find."));
                    roles.add(SuperAdminRole);
                    break;
                case "GESTIONARY":
                    Role GestionnaryRole = roleRepository.findByRoleDescription("GESTIONARY")
                            .orElseThrow(() -> new ApplicationContextException("Fail! -> Cause: User Role not find."));
                    roles.add(GestionnaryRole);
                    break;

                case "USER":
                    Role UserRole = roleRepository.findByRoleDescription("USER")
                            .orElseThrow(() -> new ApplicationContextException("Fail! -> Cause: User Role not find."));
                    roles.add(UserRole);
                    break;

                case "AUDIT":
                    Role AuditRole = roleRepository.findByRoleDescription("AUDIT")
                            .orElseThrow(() -> new ApplicationContextException("Fail! -> Cause: User Role not find."));
                    roles.add(AuditRole);
                    break;
            }

        });
        return roles;
    }

    @Override
    public User updatePasswordUser(UserDto userDto) {
        User userToUpdate = userRepository.findByEmail(userDto.getEmail());
        if (!userDto.getPassword().equals(userDto.getConfirmedPassword()))
            throw new ResourceNotFoundException("Please confirm your password");
        userToUpdate.setPassword(passwordEncoder.encode(userDto.getPassword()));
        return userToUpdate;
    }

    @Override
    public UserDetailDto getUserInfoByEmail(String email) {
        User user = userRepository.findByEmail(email);
        return UserDetailDto.builder().userId(user.getUserId())
                .userFirstName(user.getPerson().getFirstName()).userLastName(user.getPerson().getLastName())
                .userPhone(user.getPerson().getPhoneNumber())
                .build();
    }


}