package com.project.PatientManagement.repository;

import com.project.PatientManagement.entity.Invoice;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface InvoiceRepository extends CrudRepository <Invoice, Integer> {

    @Query(value = "update invoice set doctor_id=?1, issue=?2 where patient_id=?3", nativeQuery = true)
    void setInvoiceTable(int doctorId, String issue, int patientId);

    @Query(value = "select * from invoice where patient_id=?1", nativeQuery = true)
    Invoice getInvoiceByPatientId(int patientId);

    @Query(value = "select is_treated from invoice where patient_id=?1", nativeQuery = true)
    boolean isTreated(int patientId);

    @Query(value = "select is_first_time from invoice where patient_id=?1", nativeQuery = true)
    boolean isFirstTime(int patientId);

    @Query(value = "select patient_id from invoice where doctor_id=?1 and is_treated=false", nativeQuery = true)
    List<Integer> getPatientsUnderDoctor(int doctorId);

    @Query(value = "select issue from invoice where patient_id=?1", nativeQuery = true)
    String getPatientIssue(Integer patient);

    @Query(value = "update invoice set is_treated=true where patient_id=?1", nativeQuery = true)
    void setIsTreatedTrue(int patientId);

    @Query(value = "update invoice set is_first_time=false where patient_id=?1 and is_first_time=true", nativeQuery = true)
    void setIsFirstTimeFalse(int patientId);

    @Query(value = "select * from invoice where patient_id=?1", nativeQuery = true)
    Invoice findByPatientId(int patientId);
}
