package com.project.PatientManagement.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.persistence.Id;

@Entity(name = "nurse")
@Data
public class Nurse {

    @Id
    @GenericGenerator(name = "nurse_id_seq", strategy = "increment")
    @GeneratedValue(generator = "nurse_id_seq", strategy = GenerationType.AUTO)
    private int nurseId;
    private String nurseName;
    @Column(unique = true)
    private String nurseEmail;
    private String nursePassword;
    private Long nurseContact;
    private int gmail;
    @Column(columnDefinition="TEXT", length = 1000000000)
    private String accessTokenGmail;
}
