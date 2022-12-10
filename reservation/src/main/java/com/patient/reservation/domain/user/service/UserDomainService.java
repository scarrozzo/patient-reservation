package com.patient.reservation.domain.user.service;


import com.patient.reservation.controller.user.PatientSearchParameters;
import com.patient.reservation.domain.user.model.User;
import com.patient.reservation.domain.user.repository.UserRepository;
import com.patient.reservation.security.UserDetailsImpl;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Slf4j
@Service
public class UserDomainService {

    public static final String PATIENT_IDENTIFIER = "patientIdentifier";
    public static final String PATIENT_IDENTIFIER_TYPE = "patientIdentifierType";
    public static final String FIRST_NAME = "firstName";
    public static final String LAST_NAME = "lastName";
    public static final String DATE_OF_BIRTH = "dateOfBirth";
    public static final String IS_PATIENT = "isPatient";
    public static final String DOCTOR_ID = "doctorId";
    public static final String LIKE_OPERATOR = "%";

    @Autowired
    private UserRepository userRepository;

    public Page<User> getUsers(Pageable pageable){
        return userRepository.findAll(pageable);
    }

    public boolean existsByUsername(String username){
        return userRepository.existsByUsername(username);
    }

    public User getUser(String uid){
        return userRepository.findByUid(uid).orElseThrow(EntityNotFoundException::new);
    }

    public User save(User user){
        return userRepository.save(user);
    }

    public void delete(User user){
        userRepository.delete(user);
    }

    public Page<User> getPatients(PatientSearchParameters searchParameters, Pageable pageable){
        List<Specification<User>> userSpecifications = buildUserSpecifications(searchParameters);

        Iterator<Specification<User>> it = userSpecifications.iterator();
        Specification<User> combinedSpecifications = Specification.where(it.next());
        while (it.hasNext()) {
            combinedSpecifications =  combinedSpecifications.and(it.next());
        }

        return userRepository.findAll(combinedSpecifications, pageable);
    }

    public User getPatient(String uid){
        UserDetailsImpl userDetails = ((UserDetailsImpl)SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        if(Boolean.TRUE.equals(userDetails.isDoctor())){
            return userRepository.findByUidAndIsPatientAndDoctorId(uid, Boolean.TRUE, userDetails.getId()).orElseThrow(EntityNotFoundException::new);
        }
        return userRepository.findByUid(uid).orElseThrow(EntityNotFoundException::new);
    }

    private List<Specification<User>> buildUserSpecifications(PatientSearchParameters searchParameters){
        List<Specification<User>> specifications = new ArrayList<>();

        if(StringUtils.hasText(searchParameters.getPatientIdentifier())){
            specifications.add((user, q, cb) -> cb.like(user.get(PATIENT_IDENTIFIER), searchParameters.getPatientIdentifier() + LIKE_OPERATOR));
        }

        if(searchParameters.getPatientIdentifierType() != null){
            specifications.add((user, q, cb) -> cb.equal(user.get(PATIENT_IDENTIFIER_TYPE), searchParameters.getPatientIdentifier()));
        }

        if(StringUtils.hasText(searchParameters.getFirstName())){
            specifications.add((user, q, cb) -> cb.like(user.get(FIRST_NAME), searchParameters.getFirstName()+ LIKE_OPERATOR));
        }

        if(StringUtils.hasText(searchParameters.getLastName())){
            specifications.add((user, q, cb) -> cb.like(user.get(LAST_NAME), searchParameters.getLastName() + LIKE_OPERATOR));
        }

        if(searchParameters.getDateOfBirth() != null){
            specifications.add((user, q, cb) -> cb.equal(user.get(DATE_OF_BIRTH), searchParameters.getDateOfBirth()));
        }

        specifications.add((user, q, cb) -> cb.equal(user.get(IS_PATIENT), Boolean.TRUE));

        UserDetailsImpl userDetails = ((UserDetailsImpl)SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        if(Boolean.TRUE.equals(userDetails.isDoctor())) {
            specifications.add((user, q, cb) -> cb.equal(user.get(DOCTOR_ID), userDetails.getId()));
        }

        return specifications;
    }

}
