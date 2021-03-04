package com.project.PatientManagement.dto;


import lombok.Data;

import javax.persistence.Column;

@Data
public class DoctorRequestDto {

    private int doctorId;
    private String doctorName;
    private String doctorEmail;
    private String doctorPassword;
    private Long doctorContact;
    private int nurseId;
    private int gmail;
    @Column(columnDefinition="TEXT", length = 1000000000)
    private String accessTokenGmail;
}
