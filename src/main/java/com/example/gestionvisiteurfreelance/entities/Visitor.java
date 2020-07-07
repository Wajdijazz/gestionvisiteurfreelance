package com.example.gestionvisiteurfreelance.entities;


import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class Visitor {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long visitorId;

    private String firstName;

    private String lastName;
    private String email;
    private LocalDate creationDate;
    private String usercreation;


    private double phoneNumber;
    private Long NumberCI;
    private boolean isActive;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)

    private SocietyClient societyClient;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)

    private Society_Visitor societyVisitor;
}
