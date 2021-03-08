package com.project.PatientManagement.repository;


import com.project.PatientManagement.dto.PatientUnderDoctorResponseDto;
import com.project.PatientManagement.entity.Patient;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface PatientRepository extends CrudRepository<Patient, Integer> {

    Patient findByEmail(String email);

    @Query(value = "select patient_id, name, contact from patient where patient_id=?1", nativeQuery = true)
    PatientUnderDoctorResponseDto getPatient(int patientId);

    @Query(value = "select name from patient where patient_id=?1", nativeQuery = true)
    String getPatientName(Integer patient);

    @Query(value = "select contact from patient where patient_id=?1", nativeQuery = true)
    long getPatientContact(Integer patient);

    @Query(value = "select age from patient where patient_id=?1", nativeQuery = true)
    int getPatientAge(Integer patient);

    @Query(value = "select gender from patient where patient_id=?1", nativeQuery = true)
    String getPatientGender(Integer patient);

}
