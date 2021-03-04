package com.project.PatientManagement.dto;


import com.project.PatientManagement.entity.Nurse;
import lombok.Data;

import javax.persistence.Column;

@Data
public class DoctorResponseDto {

    private int doctorId;
    private String doctorName;
    private String doctorEmail;
    private Long doctorContact;
    private Nurse nurse;
    private int gmail;
    @Column(columnDefinition="TEXT", length = 1000000000)
    private String accessTokenGmail;
}
