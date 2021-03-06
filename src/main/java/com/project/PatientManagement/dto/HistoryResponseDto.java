package com.project.PatientManagement.dto;

import lombok.Data;

@Data
public class HistoryResponseDto {

    private int historyId;
    private int patientId;
    private int doctorId;
    private String patientName;
    private String doctorName;
    private String month;
    private String issue;
    private long doctorContact;
    private long patientContact;
}
