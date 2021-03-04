package com.project.PatientManagement.repository;

import com.project.PatientManagement.entity.Invoice;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface InvoiceRepository extends CrudRepository <Invoice, Integer> {

    @Query(value = "update invoice set doctor_id=?1, issue=?2 where patient_id=?3", nativeQuery = true)
    void setInvoiceTable(int patientId, String issue, int doctorId);

    @Query(value = "select * from invoice where patient_id=?1", nativeQuery = true)
    Invoice getInvoiceByPatientId(int patientId);

    @Query(value = "select is_treated from invoice where patient_id=?1", nativeQuery = true)
    boolean isTreated(int patientId);

    @Query(value = "select is_first_time from invoice where patient_id=?1", nativeQuery = true)
    boolean isFirstTime(int patientId);

    @Query(value = "select patient_id from invoice where doctor_id=?1", nativeQuery = true)
    List<Integer> getPatientsUnderDoctor(int doctorId);
}
