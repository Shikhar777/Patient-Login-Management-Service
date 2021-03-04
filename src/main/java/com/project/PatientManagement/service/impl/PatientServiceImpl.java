package com.project.PatientManagement.service.impl;

import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.project.PatientManagement.dto.LoginRequestDto;
import com.project.PatientManagement.dto.LoginResponseDto;
import com.project.PatientManagement.dto.PatientRequestDto;
import com.project.PatientManagement.dto.PatientResponseDto;
import com.project.PatientManagement.entity.Id;
import com.project.PatientManagement.entity.Patient;
import com.project.PatientManagement.repository.PatientRepository;
import com.project.PatientManagement.service.PatientService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;


@Service
public class PatientServiceImpl implements PatientService {

    @Autowired
    private PatientRepository patientRepository;

    @Override
    public PatientResponseDto saveDetails(PatientRequestDto patientRequestDto)
    {
        Patient patient = new Patient();
        Optional<Patient> optionalPatient = Optional.ofNullable(patientRepository.findByEmail(patientRequestDto.getEmail()));
        if(optionalPatient.isPresent())
        {
           return null;
        }
        BeanUtils.copyProperties(patientRequestDto, patient);
        Patient savedPatient = patientRepository.save(patient);
        PatientResponseDto patientResponseDto = new PatientResponseDto();
        BeanUtils.copyProperties(savedPatient, patientResponseDto);
        return patientResponseDto;
    }

    @Override
    public Patient findByEmail(String email)
    {
        return patientRepository.findByEmail(email);
    }

    @Override
    public Id loginPatient(LoginRequestDto loginRequestDto) throws Exception
    {
        Id id = new Id();
        Patient patient1 = findByEmail(loginRequestDto.getEmail());
        if(patient1 == null)
        {
            patient1 = new Patient();
        }
        if(loginRequestDto.getGmail() == 1)
        {
            id = loginGoogle(loginRequestDto);
            if(id.getStatus() == 200)
            {
                patient1.setEmail(loginRequestDto.getEmail());
                patient1.setGmail(1);
                patient1.setAccessTokenGmail(loginRequestDto.getAccessTokenGmail());
            }
        }
        else {
            if (loginRequestDto.getPassword().equals(patient1.getPassword())) {
                id.setId(patient1.getPatientId());
                id.setName(patient1.getName());
                id.setStatus(200);
                id.setEmail(patient1.getEmail());
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
            if(email.equals(findByEmail(email).getEmail()))
            {
                id.setId(findByEmail(email).getPatientId());
                id.setEmail(email);
                id.setStatus(200);
            }
            else
            {
                loginRequestDto.setPassword("123456789@#$");
//                LoginResponseDto login = saveDetails(loginRequestDto);
//                id.setId(login.getId());
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

    @Override
    public List<PatientResponseDto> findAll()
    {
        List<PatientResponseDto> responseDtoList = new ArrayList<>();
        Iterable<Patient> iterable = patientRepository.findAll();
        for(Patient patient : iterable)
        {
            PatientResponseDto patientResponseDto = new PatientResponseDto();
            BeanUtils.copyProperties(patient, patientResponseDto);
            responseDtoList.add(patientResponseDto);
        }
        return responseDtoList;
    }
}
