package com.project.PatientManagement.service;

import com.project.PatientManagement.dto.InvoiceRequestDto;
import com.project.PatientManagement.dto.InvoiceResponseDto;

public interface InvoiceService {
    InvoiceResponseDto makePaymentAndBookDoctor(int patientId, InvoiceRequestDto invoiceRequestDto);
}
