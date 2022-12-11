package com.patient.reservation.service.user;

import com.patient.reservation.command.user.CreatePatientCommand;
import com.patient.reservation.command.user.CreateUserCommand;
import com.patient.reservation.command.user.DeleteUserCommand;
import com.patient.reservation.command.user.UpdatePatientCommand;
import com.patient.reservation.controller.user.PatientSearchParameters;
import com.patient.reservation.domain.user.dto.PatchUserDto;
import com.patient.reservation.domain.user.dto.PostUserDto;
import com.patient.reservation.domain.user.model.User;
import com.patient.reservation.domain.user.service.UserDomainService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * This is an application service
 */
@Slf4j
@Service
public class UserService {

    @Autowired
    private UserDomainService userDomainService;

    @Autowired
    private BeanFactory beanFactory;

    /**
    * All users
    * */
    public Page<User> getUsers(Pageable pageable) {
        return userDomainService.getUsers(pageable);
    }

    public User getUser(String uid) {
        return userDomainService.getUser(uid);
    }

    public User createUser(PostUserDto postUserDto) {
        CreateUserCommand command = beanFactory.getBean(CreateUserCommand.class, postUserDto);
        return command.execute();
    }

    public void deleteUser(String uid) {
        User user = userDomainService.getUser(uid);
        DeleteUserCommand command = beanFactory.getBean(DeleteUserCommand.class, user);
        command.execute();
    }

    /**
     * Patients
     */
    public Page<User> getPatients(PatientSearchParameters searchParameters, Pageable pageable) {
        return userDomainService.getPatients(searchParameters, pageable);
    }

    public User getPatient(String uid) {
        return userDomainService.getPatient(uid);
    }

    public User createPatient(PostUserDto postUserDto) {
        CreatePatientCommand command = beanFactory.getBean(CreatePatientCommand.class, postUserDto);
        return command.execute();
    }

    public User updatePatient(String uid, PatchUserDto patchUserDto) {
        User user = userDomainService.getUser(uid);
        UpdatePatientCommand command = beanFactory.getBean(UpdatePatientCommand.class, user, patchUserDto);
        return command.execute();
    }
}
