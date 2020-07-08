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
@Table(name = "Person")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)

public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long personId;

    private String firstName;

    private String lastName;


    private double phoneNumber;


    private LocalDate creationDate;

    private boolean isActive;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)

    private Society_Visitor society;


}
