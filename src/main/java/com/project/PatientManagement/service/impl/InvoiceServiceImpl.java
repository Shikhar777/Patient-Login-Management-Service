package com.project.PatientManagement.service.impl;

import com.project.PatientManagement.dto.DoctorResponseDto;
import com.project.PatientManagement.dto.InvoiceRequestDto;
import com.project.PatientManagement.dto.InvoiceResponseDto;
import com.project.PatientManagement.entity.Invoice;
import com.project.PatientManagement.repository.InvoiceRepository;
import com.project.PatientManagement.repository.PatientRepository;
import com.project.PatientManagement.service.DoctorService;
import com.project.PatientManagement.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InvoiceServiceImpl implements InvoiceService {

    @Autowired
    DoctorService doctorService;

    @Autowired
    InvoiceRepository invoiceRepository;

    @Autowired
    PatientRepository patientRepository;

    @Override
    public InvoiceResponseDto makePaymentAndBookDoctor(int patientId, InvoiceRequestDto invoiceRequestDto) {

        Invoice invoice = invoiceRepository.getInvoiceByPatientId(patientId);
        InvoiceResponseDto invoiceResponseDto = new InvoiceResponseDto();

        String patientName = patientRepository.getPatientName(patientId);

        if(invoice == null) {
            Invoice invoice1 = new Invoice();
            invoice1.setIssue(invoiceRequestDto.getIssue());
            invoice1.setPatientId(patientId);
            invoice1.setDoctorId(invoiceRequestDto.getDoctorId());
            invoice1.setTreated(false);
            invoice1.setFirstTime(true);
            invoice1.setDoctorName(invoiceRequestDto.getDoctorName());
            invoice1.setPatientName(patientName);

            invoiceRepository.save(invoice1);
        }

        else {
            boolean isTreated = invoiceRepository.isTreated(patientId);
            if(!isTreated)
                return null;
            invoiceRepository.setInvoiceTable(patientId, invoiceRequestDto.getIssue(), patientName, invoiceRequestDto.getDoctorId());
//            boolean isFirstTime = invoiceRepository.isFirstTime(patientId);
//            invoiceResponseDto.setFirstTime(isFirstTime);
//            invoiceResponseDto.setTreated(isTreated);
            invoiceResponseDto.setTreated(false);
        }
        DoctorResponseDto doctorResponseDto = doctorService.updateDoctor(invoiceRequestDto.getDoctorId());

        invoiceResponseDto.setInvoiceId(invoiceRepository.geInvoiceID(patientId));
        invoiceResponseDto.setDoctorId(doctorResponseDto.getDoctorId());
        invoiceResponseDto.setDoctorName(doctorResponseDto.getDoctorName());
        invoiceResponseDto.setDoctorContact(doctorResponseDto.getDoctorContact());


        return invoiceResponseDto;
    }
}
