package com.project.PatientManagement.repository;

import com.project.PatientManagement.dto.HistoryResponseDto;
import com.project.PatientManagement.entity.History;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface HistoryRepository extends CrudRepository<History, Integer> {

    @Query(value = "select * from history where patient_id=?1", nativeQuery = true)
    List<History> getPatientsHistory(int patientId);
}
