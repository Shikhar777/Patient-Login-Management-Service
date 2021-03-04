package com.project.PatientManagement.dto;

import lombok.Data;

import javax.persistence.Column;

@Data
public class PatientRequestDto {

    private int patientId;
    private String name;
    private String email;
    private String password;
    private String gender;
    private Integer age;
    private String address;
    private Double height;
    private Double weight;
    private String currentMedication;
    private Long contact;
    private Long emergencyContact;
    private String admissionMonth;
    private int gmail;
    @Column(columnDefinition="TEXT", length = 1000000000)
    private String accessTokenGmail;
    private String role;
}
