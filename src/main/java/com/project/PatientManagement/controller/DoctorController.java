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

    @GetMapping(value = "/patientList/{doctorId}")
    public List<PatientUnderDoctorResponseDto> getPatientsUnderDoctor(@PathVariable("doctorId") int doctorId) {
        return doctorService.getPatientsUnderDoctor(doctorId);
    }

    @PutMapping("/endConsultation")
    public void endConsultation(@RequestBody HistoryRequestDto historyRequestDto) {
         doctorService.endConsultation(historyRequestDto);
    }
}
