package com.project.PatientManagement.service;

import com.project.PatientManagement.dto.LoginRequestDto;
import com.project.PatientManagement.dto.NurseRequestDto;
import com.project.PatientManagement.dto.NurseResponseDto;
import com.project.PatientManagement.dto.PatientUnderDoctorResponseDto;
import com.project.PatientManagement.entity.Id;
import com.project.PatientManagement.entity.Nurse;
import com.project.PatientManagement.entity.NurseId;

import java.util.List;

public interface NurseService {

    NurseResponseDto saveDetails(NurseRequestDto nurseRequestDto);
    Id loginNurse(LoginRequestDto loginRequestDto) throws Exception;
    Nurse findByEmail(String email);
}
