package com.example.gestionvisiteurfreelance.Security;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class JwtResponse {
    private String token;
    private String type = "Bearer";
    private String username;
    private Collection<? extends GrantedAuthority> authorities;

    public JwtResponse(String token, String type, String username, Collection<? extends GrantedAuthority> authorities) {
        this.token = token;
        this.type = type;
        this.username = username;
        this.authorities = authorities;
    }
}
