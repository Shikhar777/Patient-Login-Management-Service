package com.project.PatientManagement.controller;

import com.project.PatientManagement.dto.*;
import com.project.PatientManagement.service.DoctorService;
import com.project.PatientManagement.service.InvoiceService;
import com.project.PatientManagement.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping (value = "/patient")
@CrossOrigin("*")
public class PatientController {

    @Autowired
    private PatientService patientService;

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private InvoiceService invoiceService;


    @PostMapping(value = "/registration")
    public PatientResponseDto saveDetails(@RequestBody PatientRequestDto patientRequestDto) {
        return patientService.saveDetails(patientRequestDto);
    }

    @GetMapping(value = "/doctorList")
    public List<DoctorResponseDto> getDoctorsList() {
        return doctorService.getDoctorsList();
    }


    @GetMapping(value = "/register/findAll")
    public List<PatientResponseDto> findAll()
    {
        return patientService.findAll();
    }

    @PutMapping("/makePayment/{patientId}")
    public InvoiceResponseDto makePaymentAndBookDoctor(@PathVariable("patientId") int patientId, @RequestBody InvoiceRequestDto invoiceRequestDto) {
        return invoiceService.makePaymentAndBookDoctor(patientId, invoiceRequestDto);
    }

    @GetMapping("/getPatientsHistory/{patientId}")
    public List<HistoryResponseDto> getPatientsHistory(@PathVariable("patientId") int patientId) {
        return patientService.getPatientsHistory(patientId);
    }
}
