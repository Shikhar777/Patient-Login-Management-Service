package com.project.PatientManagement.service.impl;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.project.PatientManagement.dto.LoginRequestDto;
import com.project.PatientManagement.dto.NurseRequestDto;
import com.project.PatientManagement.dto.NurseResponseDto;
import com.project.PatientManagement.entity.Id;
import com.project.PatientManagement.entity.Nurse;
import com.project.PatientManagement.repository.InvoiceRepository;
import com.project.PatientManagement.repository.NurseRepository;
import com.project.PatientManagement.service.NurseService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;


@Service
public class NurseServiceImpl implements NurseService {

    @Autowired
    private NurseRepository nurseRepository;

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Override
    public NurseResponseDto saveDetails(NurseRequestDto nurseRequestDto)
    {
        Nurse nurse = new Nurse();
        BeanUtils.copyProperties(nurseRequestDto, nurse);
        Nurse savedNurse = nurseRepository.save(nurse);
        NurseResponseDto nurseResponseDto = new NurseResponseDto();
        BeanUtils.copyProperties(savedNurse, nurseResponseDto);
        return nurseResponseDto;
    }

    @Override
    public Nurse findByEmail(String email)
    {
        return nurseRepository.findByNurseEmail(email);
    }

    @Override
    public Id loginNurse(LoginRequestDto loginRequestDto) throws Exception
    {
        Id id = new Id();
        Nurse nurse1 = findByEmail(loginRequestDto.getEmail());
        if(nurse1 == null)
        {
            nurse1 = new Nurse();
        }
        if(loginRequestDto.getGmail() == 1)
        {
            id = loginGoogle(loginRequestDto);
            if(id.getStatus() == 200)
            {
                nurse1.setNurseEmail(loginRequestDto.getEmail());
                nurse1.setGmail(1);
                nurse1.setAccessTokenGmail(loginRequestDto.getAccessTokenGmail());
            }
        }
        else {
            if (loginRequestDto.getPassword().equals(nurse1.getNursePassword())) {
                id.setId(nurse1.getNurseId());
                id.setName(nurse1.getNurseName());
                id.setStatus(200);
                id.setEmail(nurse1.getNurseEmail());
            }
            else
            {
                id.setId(-1);
                id.setStatus(400);
            }
        }
        return id;
    }

    Id loginGoogle(LoginRequestDto loginRequestDto) throws Exception
    {
        Id id = new Id();
        GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(new NetHttpTransport(), new JacksonFactory())
                .setAudience(Collections.singletonList("534036484353-sm512cc753bn8pcla13l7v50k6253g5g.apps.googleusercontent.com"))
                .build();
        GoogleIdToken idToken = verifier.verify(loginRequestDto.getAccessTokenGmail());
        if(idToken != null)
        {
            GoogleIdToken.Payload payload = idToken.getPayload();
            String patientId = payload.getSubject();
            System.out.println("Patient Id: "+patientId);
            String email = payload.getEmail();
            System.out.println("Email: "+email);
            if(email.equals(findByEmail(email).getNurseEmail()))
            {
                id.setId(findByEmail(email).getNurseId());
                id.setEmail(email);
                id.setStatus(200);
            }
            else
            {
                loginRequestDto.setPassword("123456789@#$");
                id.setStatus(200);
            }
            System.out.println(email);
        }
        else
        {
            id.setStatus(400);
            System.out.println("Invalid Id Token");
        }
        return id;
    }
}
