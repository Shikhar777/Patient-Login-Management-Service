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
public class PatientController {

    @Autowired
    private PatientService patientService;

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private InvoiceService invoiceService;

    @CrossOrigin
    @PostMapping(value = "/registration")
    public PatientResponseDto saveDetails(@RequestBody PatientRequestDto patientRequestDto) {
        return patientService.saveDetails(patientRequestDto);
    }

//    @CrossOrigin(origins = "*")
//    @PostMapping(value = "/login")
//    public Id loginPatient(@RequestBody PatientRequestDto patientRequestDto) throws Exception {
//        return patientService.loginPatient(patientRequestDto);
//    }

    @CrossOrigin
    @GetMapping(value = "/doctorList")
    public List<DoctorResponseDto> getDoctorsList() {
        return doctorService.getDoctorsList();
    }

//    @CrossOrigin
//    @PutMapping("/doctorList/{doctorId}")
//    public DoctorResponseDto updateDoctor(@PathVariable int doctorId) {
//        return doctorService.updateDoctor(doctorId);
//    }

    @CrossOrigin
    @GetMapping(value = "/register/findAll")
    public List<PatientResponseDto> findAll()
    {
        return patientService.findAll();
    }

    @CrossOrigin
    @PutMapping("/makePayment/{patientId}")
    public InvoiceResponseDto makePaymentAndBookDoctor(@PathVariable("patientId") int patientId, @RequestBody InvoiceRequestDto invoiceRequestDto) {
        return invoiceService.makePaymentAndBookDoctor(patientId, invoiceRequestDto);
    }
}
