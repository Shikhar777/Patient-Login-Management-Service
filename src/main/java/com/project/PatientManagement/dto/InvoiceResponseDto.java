package com.project.PatientManagement.dto;

import lombok.Data;

@Data
public class InvoiceResponseDto {

    private int invoiceId;
    private int doctorId;
    private String doctorName;
    private String patientName;
    private long doctorContact;
    private boolean isTreated;
    private boolean isFirstTime;
}
