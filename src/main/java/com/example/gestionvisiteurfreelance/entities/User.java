package com.example.gestionvisiteurfreelance.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.*;
import com.fasterxml.jackson.annotation.JsonAutoDetect;

@Entity
@Data
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    private String email;

    @JsonProperty(access=JsonProperty.Access.WRITE_ONLY)
    private String password;

    private boolean isActive;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)

    private Employee person;
    @ManyToMany()
    private Set<Role> roles = new HashSet<>();



}
