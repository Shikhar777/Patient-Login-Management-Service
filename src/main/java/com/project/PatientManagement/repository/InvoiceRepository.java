package com.project.PatientManagement.repository;

import com.project.PatientManagement.entity.Invoice;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface InvoiceRepository extends CrudRepository <Invoice, Integer> {

    @Query(value = "update invoice set doctor_id=?1, issue=?2, patient_name=?3 where patient_id=?4", nativeQuery = true)
    void setInvoiceTable(int doctorId, String issue, String patientName, int patientId);

    @Query(value = "select * from invoice where patient_id=?1", nativeQuery = true)
    Invoice getInvoiceByPatientId(int patientId);

    @Query(value = "select is_treated from invoice where patient_id=?1", nativeQuery = true)
    boolean isTreated(int patientId);

    @Query(value = "select patient_id from invoice where doctor_id=?1 and is_treated=false", nativeQuery = true)
    List<Integer> getPatientsUnderDoctor(int doctorId);

    @Query(value = "select issue from invoice where patient_id=?1", nativeQuery = true)
    String getPatientIssue(Integer patient);

    @Query(value = "delete from invoice where patient_id=?1", nativeQuery = true)
    int deleteByPatientId(int patientId);

    @Query(value = "select invoice_id from invoice where patient_id=?1", nativeQuery = true)
    int geInvoiceID(int patientId);
}
