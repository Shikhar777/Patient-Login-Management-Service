package com.project.PatientManagement.dto;

import lombok.Data;

@Data
public class LoginResponseDto {

    private int id;
    private int status;
    private String name;
    private String email;
}
