package com.patient.reservation.command.user;

import com.patient.core.command.BaseTransactionalCommand;
import com.patient.reservation.domain.user.dto.PostUserDto;
import com.patient.reservation.domain.user.model.RoleType;
import com.patient.reservation.domain.user.model.User;
import com.patient.reservation.exception.PermissionDeniedException;
import com.patient.reservation.exception.ServiceError;
import com.patient.reservation.security.UserDetailsImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Slf4j
@Scope("prototype")
@Component
public class CreatePatientCommand extends BaseTransactionalCommand<User> {

    @Autowired
    private BeanFactory beanFactory;

    private final PostUserDto dto;

    public CreatePatientCommand(PostUserDto dto){
        this.dto = dto;
    }

    @Override
    public User doExecute(){
        if (dto.getRoles() != null && dto.getRoles().stream().anyMatch(p -> !p.equals(RoleType.READER))) {
            throw new IllegalArgumentException(ServiceError.E0005.getMessage());
        }

        if (Boolean.FALSE.equals(dto.getIsPatient())) {
            throw new PermissionDeniedException(ServiceError.E0006.getMessage());
        }

        if (Boolean.TRUE.equals(dto.getIsDoctor())) {
            throw new PermissionDeniedException(ServiceError.E0007.getMessage());
        }

        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (Boolean.TRUE.equals(userDetails.isDoctor()) && !userDetails.getUid().equals(dto.getDoctorUid())) {
            throw new PermissionDeniedException(ServiceError.E0009.getMessage());
        }

        CreateUserCommand command = beanFactory.getBean(CreateUserCommand.class, dto);
        return command.execute();
    }
}
