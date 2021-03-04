package com.project.PatientManagement.repository;

import com.project.PatientManagement.entity.Doctor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface DoctorRepository extends CrudRepository<Doctor, Integer> {

    @Query(value = "select * from doctor where doctor_patient_count < 5", nativeQuery = true)
    List<Doctor> getDoctorsList();
    Doctor findByDoctorEmail(String doctorEmail);

    @Query(value = "select doctor_id from doctor where nurse_id=?1", nativeQuery = true)
    int getNurseId(int nurseId);

}
