package com.project.PatientManagement.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.persistence.Id;

@Entity (name = "patient")
@Data
public class Patient {

    @Id
    @GenericGenerator(name = "patient_id_seq", strategy = "increment")
    @GeneratedValue(generator = "patient_id_seq", strategy = GenerationType.AUTO)
    private int patientId;
    private String name;
    @Column(unique = true)
    private String email;
    private String password;
    private String gender;
    private Integer age;
    private String address;
    private Double height;
    private Double weight;
    private String currentMedication;
    private Long contact;
    private Long emergencyContact;
    private String admissionMonth;
    private int gmail;
    @Column(columnDefinition="TEXT", length = 1000000000)
    private String accessTokenGmail;
}
