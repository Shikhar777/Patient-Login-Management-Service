package com.project.PatientManagement.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.persistence.Id;

@Entity(name = "doctor")
@Data
public class Doctor {

    @Id
    @GenericGenerator(name = "doctor_id_seq", strategy = "increment")
    @GeneratedValue(generator = "doctor_id_seq", strategy = GenerationType.AUTO)
    private int doctorId;
    private String doctorName;
    @Column(unique = true)
    private String doctorEmail;
    private String doctorPassword;
    private int doctorPatientCount;
    private Long doctorContact;
    @OneToOne
    @JoinColumn(name = "nurseId")
    private Nurse nurse;
    private int gmail;
    @Column(columnDefinition="TEXT", length = 1000000000)
    private String accessTokenGmail;

}
