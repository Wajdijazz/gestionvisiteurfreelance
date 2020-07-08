package com.example.gestionvisiteurfreelance.Dto;
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

public class UserDetailDto {
    private Long userId;

    private Long companyId;

    private String companyName;

    private String userFirstName;

    private String userLastName;

    private String companyWebSite;

    private double userPhone;


}
