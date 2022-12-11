package com.patient.reservation.command.user;

import com.patient.core.command.BaseTransactionalCommand;
import com.patient.reservation.domain.user.dto.PatchUserDto;
import com.patient.reservation.domain.user.model.User;
import com.patient.reservation.domain.user.service.UserDomainService;
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
public class UpdatePatientCommand extends BaseTransactionalCommand<User> {

    @Autowired
    private UserDomainService userDomainService;

    @Autowired
    private BeanFactory beanFactory;

    private final PatchUserDto dto;
    private User user;

    public UpdatePatientCommand(User user, PatchUserDto dto){
        this.dto = dto;
        this.user = user;
    }

    @Override
    protected User doExecute() {
        if (!Boolean.TRUE.equals(user.getIsPatient())) {
            throw new PermissionDeniedException(ServiceError.E0008.getMessage());
        }

        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (Boolean.TRUE.equals(userDetails.isDoctor()) && !userDetails.getId().equals(user.getDoctorId())) {
            throw new PermissionDeniedException(ServiceError.E0010.getMessage());
        }

        UpdateUserCommand command = beanFactory.getBean(UpdateUserCommand.class, user, dto);
        return command.execute();
    }

}
