package com.project.PatientManagement.controller;

import com.project.PatientManagement.dto.LoginRequestDto;
import com.project.PatientManagement.dto.LoginResponseDto;
import com.project.PatientManagement.entity.Id;
import com.project.PatientManagement.service.DoctorService;
import com.project.PatientManagement.service.NurseService;
import com.project.PatientManagement.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/login")
public class LoginController {

    @Autowired
    PatientService patientService;

    @Autowired
    DoctorService doctorService;

    @Autowired
    NurseService nurseService;

    @CrossOrigin
    @PostMapping(value = "/")
    public Id userLogin(@RequestBody LoginRequestDto loginRequestDto) throws Exception {
        if(loginRequestDto.getRole().equals("Patient")) {
            return patientService.loginPatient(loginRequestDto);
        }
        else if(loginRequestDto.getRole().equals("Doctor")) {
            return doctorService.loginDoctor(loginRequestDto);
        }
        return nurseService.loginNurse(loginRequestDto);
    }
}
