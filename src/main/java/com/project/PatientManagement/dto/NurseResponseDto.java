package com.project.PatientManagement.dto;

import lombok.Data;

import javax.persistence.Column;

@Data
public class NurseResponseDto {

    private int nurseId;
    private String nurseName;
    private String nurseEmail;
    private Long nurseContact;
    private int gmail;
    @Column(columnDefinition="TEXT", length = 1000000000)
    private String accessTokenGmail;
}
