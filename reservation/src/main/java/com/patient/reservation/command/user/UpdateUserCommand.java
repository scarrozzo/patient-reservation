package com.patient.reservation.command.user;

import com.patient.core.command.BaseTransactionalCommand;
import com.patient.reservation.domain.user.dto.PatchUserDto;
import com.patient.reservation.domain.user.factory.UserDtoMapper;
import com.patient.reservation.domain.user.model.User;
import com.patient.reservation.domain.user.service.UserDomainService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Slf4j
@Scope("prototype")
@Component
public class UpdateUserCommand extends BaseTransactionalCommand<User> {

    @Autowired
    private UserDomainService userDomainService;

    @Autowired
    private UserDtoMapper userMapper;

    private final PatchUserDto dto;
    private User user;

    public UpdateUserCommand(User user, PatchUserDto dto){
        this.dto = dto;
        this.user = user;
    }

    @Override
    protected User doExecute() {
        log.info("Updating {} with {}...", user, dto);
        user = userMapper.patch(user, dto);
        user = userDomainService.save(user);
        log.info("{} updated.", user);
        return user;
    }

}
