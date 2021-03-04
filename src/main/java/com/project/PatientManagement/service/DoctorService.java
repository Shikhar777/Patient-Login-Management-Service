package com.project.PatientManagement.service;

import com.project.PatientManagement.dto.DoctorRequestDto;
import com.project.PatientManagement.dto.DoctorResponseDto;
import com.project.PatientManagement.dto.LoginRequestDto;
import com.project.PatientManagement.dto.PatientUnderDoctorResponseDto;
import com.project.PatientManagement.entity.Doctor;
import com.project.PatientManagement.entity.Id;

import java.util.List;

public interface DoctorService {

    DoctorResponseDto updateDoctor(int doctorId);
    DoctorResponseDto saveDetails(DoctorRequestDto doctorRequestDto);
    List<DoctorResponseDto> getDoctorsList();
    Id loginDoctor(LoginRequestDto loginRequestDto) throws Exception;
    Doctor findByEmail(String email);

    List<PatientUnderDoctorResponseDto> getPatientsUnderDoctor(int doctorId);
}
