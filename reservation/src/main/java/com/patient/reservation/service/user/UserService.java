package com.patient.reservation.service.user;

import com.patient.reservation.command.user.CreateUserCommand;
import com.patient.reservation.command.user.DeleteUserCommand;
import com.patient.reservation.command.user.UpdateUserCommand;
import com.patient.reservation.controller.user.PatientSearchParameters;
import com.patient.reservation.domain.user.dto.PatchUserDto;
import com.patient.reservation.domain.user.dto.PostUserDto;
import com.patient.reservation.domain.user.model.RoleType;
import com.patient.reservation.domain.user.model.User;
import com.patient.reservation.domain.user.service.UserDomainService;
import com.patient.reservation.exception.PermissionDeniedException;
import com.patient.reservation.exception.ServiceError;
import com.patient.reservation.security.UserDetailsImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
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
        if (postUserDto.getRoles() != null && postUserDto.getRoles().stream().anyMatch(p -> !p.equals(RoleType.READER))) {
            throw new IllegalArgumentException(ServiceError.E0005.getMessage());
        }

        if (Boolean.FALSE.equals(postUserDto.getIsPatient())) {
            throw new PermissionDeniedException(ServiceError.E0006.getMessage());
        }

        if (Boolean.TRUE.equals(postUserDto.getIsDoctor())) {
            throw new PermissionDeniedException(ServiceError.E0007.getMessage());
        }

        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (Boolean.TRUE.equals(userDetails.isDoctor()) && !userDetails.getUid().equals(postUserDto.getDoctorUid())) {
            throw new PermissionDeniedException(ServiceError.E0009.getMessage());
        }

        return createUser(postUserDto);
    }

    public User updatePatient(String uid, PatchUserDto patchUserDto) {
        User user = userDomainService.getUser(uid);

        if (!Boolean.TRUE.equals(user.getIsPatient())) {
            throw new PermissionDeniedException(ServiceError.E0008.getMessage());
        }

        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (Boolean.TRUE.equals(userDetails.isDoctor()) && !userDetails.getId().equals(user.getDoctorId())) {
            throw new PermissionDeniedException(ServiceError.E0010.getMessage());
        }

        UpdateUserCommand command = beanFactory.getBean(UpdateUserCommand.class, user, patchUserDto);
        return command.execute();
    }
}
