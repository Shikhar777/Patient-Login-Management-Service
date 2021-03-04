package com.project.PatientManagement.controller;


import com.project.PatientManagement.dto.DoctorRequestDto;
import com.project.PatientManagement.dto.DoctorResponseDto;
import com.project.PatientManagement.dto.PatientUnderDoctorResponseDto;
import com.project.PatientManagement.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/doctor")
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    @PostMapping(value = "/")
    public DoctorResponseDto saveDetails(@RequestBody DoctorRequestDto doctorRequestDto)
    {
        return doctorService.saveDetails(doctorRequestDto);
    }

//    @CrossOrigin(origins = "*")
//    @PostMapping(value = "/login")
//    public DoctorId loginDoctor(@RequestBody DoctorRequestDto doctorRequestDto) throws Exception
//    {
//        return doctorService.loginDoctor(doctorRequestDto);
//    }

    @CrossOrigin
    @GetMapping(value = "/patientList/{doctorId}")
    public List<PatientUnderDoctorResponseDto> getPatientsUnderDoctor(@PathVariable("doctorId") int doctorId) {
        return doctorService.getPatientsUnderDoctor(doctorId);
    }

    @CrossOrigin
    @PutMapping("endConsultation/{doctorId}/{patientId}")
    public void endConsultation(@PathVariable("doctorId") int doctorId, @PathVariable("patientId") int patientId) {
         doctorService.endConsultation(doctorId, patientId);
    }
}
