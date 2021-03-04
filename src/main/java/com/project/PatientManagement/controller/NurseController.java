package com.project.PatientManagement.controller;


import com.project.PatientManagement.dto.NurseRequestDto;
import com.project.PatientManagement.dto.NurseResponseDto;
import com.project.PatientManagement.dto.PatientUnderDoctorResponseDto;
import com.project.PatientManagement.entity.NurseId;
import com.project.PatientManagement.repository.DoctorRepository;
import com.project.PatientManagement.service.DoctorService;
import com.project.PatientManagement.service.NurseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/nurse")
public class NurseController {

    @Autowired
    private NurseService nurseService;

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private DoctorService doctorService;

    @CrossOrigin
    @PostMapping(value = "/")
    public NurseResponseDto saveDetails(@RequestBody NurseRequestDto nurseRequestDto)
    {
        return nurseService.saveDetails(nurseRequestDto);
    }

//    @CrossOrigin(origins = "*")
//    @PostMapping(value = "/login")
//    public NurseId loginNurse(@RequestBody NurseRequestDto nurseRequestDto) throws Exception
//    {
//        return nurseService.loginNurse(nurseRequestDto);
//    }

    @CrossOrigin
    @GetMapping(value = "/patientList/{nurseId}")
    public List<PatientUnderDoctorResponseDto> getPatientsUnderDoctor(@PathVariable("nurseId") int nurseId) {

        int doctorId = doctorRepository.getNurseId(nurseId);
        return doctorService.getPatientsUnderDoctor(doctorId);
    }
}
