package com.project.PatientManagement.repository;

import com.project.PatientManagement.entity.Nurse;
import org.springframework.data.repository.CrudRepository;

public interface NurseRepository extends CrudRepository<Nurse, Integer> {

    Nurse findByNurseEmail(String nurseEmail);
}
