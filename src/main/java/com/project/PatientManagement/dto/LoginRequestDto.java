package com.project.PatientManagement.dto;

import lombok.Data;

import javax.persistence.Column;

@Data
public class LoginRequestDto {


    private String email;
    private String password;
    private String role;
    private int gmail;
    @Column(columnDefinition="TEXT", length = 1000000000)
    private String accessTokenGmail;
}
