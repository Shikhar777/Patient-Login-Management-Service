package com.project.PatientManagement.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "history")
@Data
public class History {

    @Id
    @GenericGenerator(name = "history_id_seq", strategy = "increment")
    @GeneratedValue(generator = "history_id_seq", strategy = GenerationType.AUTO)
    private int historyId;
    private int patientId;
    private int doctorId;
    private String patientName;
    private String doctorName;
    private String month;
    private String issue;
}
