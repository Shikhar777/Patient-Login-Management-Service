package com.project.PatientManagement.controller;


import com.project.PatientManagement.dto.*;
import com.project.PatientManagement.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/doctor")
@CrossOrigin("*")
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    @PostMapping(value = "/")
    public DoctorResponseDto saveDetails(@RequestBody DoctorRequestDto doctorRequestDto)
    {
        return doctorService.saveDetails(doctorRequestDto);
    }

//
//    @PostMapping(value = "/login")
//    public DoctorId loginDoctor(@RequestBody DoctorRequestDto doctorRequestDto) throws Exception
//    {
//        return doctorService.loginDoctor(doctorRequestDto);
//    }

    @GetMapping(value = "/patientList/{doctorId}")
    public List<PatientUnderDoctorResponseDto> getPatientsUnderDoctor(@PathVariable("doctorId") int doctorId) {
        return doctorService.getPatientsUnderDoctor(doctorId);
    }

    @PutMapping("/endConsultation")
    public void endConsultation(@RequestBody HistoryRequestDto historyRequestDto) {
         doctorService.endConsultation(historyRequestDto);
    }

    @GetMapping("/getPatientsHistory/{patientId}")
    public List<HistoryResponseDto> getPatientsHistory(@PathVariable("patientId") int patientId) {
        return doctorService.getPatientsHistory(patientId);
    }
}
