package com.project.PatientManagement.service;

import com.project.PatientManagement.dto.LoginRequestDto;
import com.project.PatientManagement.dto.PatientRequestDto;
import com.project.PatientManagement.dto.PatientResponseDto;
import com.project.PatientManagement.entity.Id;
import com.project.PatientManagement.entity.Patient;

import java.util.List;


public interface PatientService {

    PatientResponseDto saveDetails(PatientRequestDto patientRequestDto);
    Id loginPatient(LoginRequestDto loginRequestDto) throws Exception;
    Patient findByEmail(String email);
    List<PatientResponseDto> findAll();
}
