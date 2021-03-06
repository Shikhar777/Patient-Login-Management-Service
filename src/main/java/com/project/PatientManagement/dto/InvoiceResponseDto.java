package com.project.PatientManagement.dto;

import lombok.Data;

@Data
public class InvoiceResponseDto {

    private int invoiceId;
    private int doctorId;
    private String doctorName;
    private long doctorContact;
    private boolean isTreated;
    private boolean isFirstTime;
}
