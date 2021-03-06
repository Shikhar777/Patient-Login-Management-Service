package com.project.PatientManagement.service;

import com.project.PatientManagement.dto.*;
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

    void endConsultation(HistoryRequestDto historyRequestDto);

    List<HistoryResponseDto> getPatientsHistory(int patientId);
}
