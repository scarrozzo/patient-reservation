package com.patient.reservation.command.user;

import com.patient.core.command.BaseTransactionalCommand;
import com.patient.reservation.domain.user.dto.PostUserDto;
import com.patient.reservation.domain.user.factory.UserFactory;
import com.patient.reservation.domain.user.model.Role;
import com.patient.reservation.domain.user.model.User;
import com.patient.reservation.domain.user.service.RoleDomainService;
import com.patient.reservation.domain.user.service.UserDomainService;
import com.patient.reservation.exception.ServiceError;
import jakarta.persistence.EntityExistsException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Set;

@Slf4j
@Scope("prototype")
@Component
public class CreateUserCommand extends BaseTransactionalCommand<User> {

    @Autowired
    private UserFactory userFactory;

    @Autowired
    private RoleDomainService roleDomainService;

    @Autowired
    private UserDomainService userDomainService;

    private final PostUserDto dto;

    public CreateUserCommand(PostUserDto dto){
        this.dto = dto;
    }

    @Override
    public User doExecute(){
        Set<Role> roles = null;
        // retrieve dto roles
        if(dto.getRoles() != null && !dto.getRoles().isEmpty()){
            roles = roleDomainService.findByNameIn(dto.getRoles());
            if(roles.size() != dto.getRoles().size()){
                log.error("One or more invalid roles: {}", dto.getRoles());
                throw new IllegalArgumentException("One or more invalid roles");
            }
            log.debug("Found {} roles", roles);
        }

        // check that username is available
        if(userDomainService.existsByUsername(dto.getUsername())){
            log.error("User with username {} already exists.", dto.getUsername());
            throw new EntityExistsException(ServiceError.E0002.getMessage());
        }

        User doctor = null;
        if(StringUtils.hasText(dto.getDoctorUid())) {
            doctor = userDomainService.getUser(dto.getDoctorUid());
        }

        // create user
        log.info("Creating new user...");
        User user = userFactory.createUser(dto, doctor, roles);
        user = userDomainService.save(user);
        log.info("{} created.", user);
        return user;
    }
}
