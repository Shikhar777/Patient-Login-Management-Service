package com.project.PatientManagement.service.impl;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.project.PatientManagement.dto.*;
import com.project.PatientManagement.entity.*;
import com.project.PatientManagement.repository.*;
import com.project.PatientManagement.service.DoctorService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;


@Service
public class DoctorServiceImpl implements DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private NurseRepository nurseRepository;

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private HistoryRepository historyRepository;

    @Override
    public DoctorResponseDto saveDetails(DoctorRequestDto doctorRequestDto)
    {
        Doctor doctor = new Doctor();
        BeanUtils.copyProperties(doctorRequestDto, doctor);
        Optional<Nurse> optionalNurse = nurseRepository.findById(doctorRequestDto.getNurseId());
        Nurse nurse = null;
        if(optionalNurse.isPresent())
        {
            nurse = optionalNurse.get();
        }
        doctor.setNurse(nurse);
        Doctor savedDoctor = doctorRepository.save(doctor);
        DoctorResponseDto doctorResponseDto = new DoctorResponseDto();
        BeanUtils.copyProperties(savedDoctor, doctorResponseDto);
        return doctorResponseDto;
    }

    @Override
    public DoctorResponseDto updateDoctor(int doctorId) {
        Doctor doctor = doctorRepository.findById(doctorId).get();
        int doctorPatientCount = doctor.getDoctorPatientCount();
        if(doctorPatientCount == 5) {
            return null;
        }

        doctorPatientCount = doctorPatientCount + 1;
        doctor.setDoctorPatientCount(doctorPatientCount);
        Doctor savedDoctor = doctorRepository.save(doctor);
        DoctorResponseDto doctorResponseDto = new DoctorResponseDto();
        BeanUtils.copyProperties(savedDoctor, doctorResponseDto);
        return doctorResponseDto;
    }

    @Override
    public List<DoctorResponseDto> getDoctorsList()
    {
        List<Doctor> doctorList = doctorRepository.getDoctorsList();
        List<DoctorResponseDto> doctorResponseDtoList = new ArrayList<>();
        for(Doctor doctor : doctorList)
        {
            DoctorResponseDto doctorResponseDto = new DoctorResponseDto();
            doctorResponseDto.setDoctorId(doctor.getDoctorId());
            doctorResponseDto.setDoctorName(doctor.getDoctorName());
            doctorResponseDto.setDoctorEmail(doctor.getDoctorEmail());
            doctorResponseDto.setDoctorContact(doctor.getDoctorContact());
            doctorResponseDtoList.add(doctorResponseDto);
        }
        return doctorResponseDtoList;
    }

    @Override
    public Doctor findByEmail(String email)
    {
        return doctorRepository.findByDoctorEmail(email);
    }

    @Override
    public List<PatientUnderDoctorResponseDto> getPatientsUnderDoctor(int doctorId) {

        List<Integer> patientId = invoiceRepository.getPatientsUnderDoctor(doctorId);
        if(patientId == null)
            return null;
        List<PatientUnderDoctorResponseDto> patientUnderDoctorResponseDtos = new ArrayList<>();

        for(Integer patient: patientId) {
            String patientName = patientRepository.getPatientName(patient);
            long patientContact = patientRepository.getPatientContact(patient);
            String gender = patientRepository.getPatientGender(patient);
            int age = patientRepository.getPatientAge(patient);
            String issue = invoiceRepository.getPatientIssue(patient);
            PatientUnderDoctorResponseDto patientUnderDoctorResponseDto = new PatientUnderDoctorResponseDto();
            patientUnderDoctorResponseDto.setId(patient);
            patientUnderDoctorResponseDto.setPatientContact(patientContact);
            patientUnderDoctorResponseDto.setPatientName(patientName);
            patientUnderDoctorResponseDto.setAge(age);
            patientUnderDoctorResponseDto.setGender(gender);
            patientUnderDoctorResponseDto.setIssue(issue);
            patientUnderDoctorResponseDtos.add(patientUnderDoctorResponseDto);
        }

        return patientUnderDoctorResponseDtos;
    }

    @Override
    public void endConsultation(HistoryRequestDto historyRequestDto) {

        int patientId = historyRequestDto.getPatientId();
        int doctorId = historyRequestDto.getDoctorId();
        String month = historyRequestDto.getMonth();

        //boolean isTreated = invoiceRepository.isTreated(patientId);
        Invoice invoice = invoiceRepository.findByPatientId(patientId);

//        if(!isTreated) {
//            invoice.setTreated(true);
//        }
//
//
//        boolean isFirstTIme = invoiceRepository.isFirstTime(patientId);
//        if(isFirstTIme) {
//            invoice.setFirstTime(false);
//        }

        //invoiceRepository.save(invoice);
        Doctor doctor = doctorRepository.findById(doctorId).get();
        int doctorPatientCount = doctor.getDoctorPatientCount();
        doctorPatientCount = doctorPatientCount - 1;
        doctor.setDoctorPatientCount(doctorPatientCount);
        doctorRepository.save(doctor);

        String doctorName = doctorRepository.getDoctorName(doctorId);
        String patientName = patientRepository.getPatientName(patientId);
        String issue = invoiceRepository.getPatientIssue(patientId);

        History history = new History();
        history.setDoctorId(doctorId);
        history.setDoctorName(doctorName);
        history.setPatientId(patientId);
        history.setPatientName(patientName);
        history.setMonth(month);
        history.setIssue(issue);

        historyRepository.save(history);

        try {
            invoiceRepository.deleteByPatientId(patientId);
        }
        catch (Exception e){}
    }

    @Override
    public List<HistoryResponseDto> getPatientsHistory(int patientId) {

        List<History> history = historyRepository.getPatientsHistory(patientId);
        List<HistoryResponseDto> historyResponseDtos = new ArrayList<>();



        for(History history1: history) {
            HistoryResponseDto historyResponseDto = new HistoryResponseDto();
            historyResponseDto.setDoctorId(history1.getDoctorId());
            long doctorContact = doctorRepository.getDoctorContact(history1.getDoctorId());
            historyResponseDto.setDoctorContact(doctorContact);
            historyResponseDto.setDoctorName(history1.getDoctorName());
            historyResponseDto.setPatientId(history1.getPatientId());
            historyResponseDto.setPatientName(history1.getPatientName());
            historyResponseDto.setIssue(history1.getIssue());
            historyResponseDto.setMonth(history1.getMonth());
            historyResponseDto.setPatientContact(patientRepository.getPatientContact(patientId));
            historyResponseDtos.add(historyResponseDto);
        }


        return historyResponseDtos;
    }

    @Override
    public Id loginDoctor(LoginRequestDto loginRequestDto) throws Exception
    {
        Id id = new Id();
        Doctor doctor1 = findByEmail(loginRequestDto.getEmail());
        if(doctor1 == null)
        {
            doctor1 = new Doctor();
        }
        if(loginRequestDto.getGmail() == 1)
        {
            id = loginGoogle(loginRequestDto);
            if(id.getStatus() == 200)
            {
                doctor1.setDoctorEmail(loginRequestDto.getEmail());
                doctor1.setGmail(1);
                doctor1.setAccessTokenGmail(loginRequestDto.getAccessTokenGmail());
            }
        }
        else {
            if (loginRequestDto.getPassword().equals(doctor1.getDoctorPassword())) {
                id.setId(doctor1.getDoctorId());
                id.setName(doctor1.getDoctorName());
                id.setStatus(200);
                id.setEmail(doctor1.getDoctorEmail());
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
            if(email.equals(findByEmail(email).getDoctorEmail()))
            {
                id.setId(findByEmail(email).getDoctorId());
                id.setEmail(email);
                id.setStatus(200);
            }
            else
            {
                loginRequestDto.setPassword("123456789@#$");
//                DoctorResponseDto doctor = saveDetails(doctorRequestDto);
//                id.setId(doctor.getDoctorId());
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
