package com.patient.reservation.command.user;

import com.patient.core.command.BaseTransactionalCommand;
import com.patient.reservation.domain.user.model.User;
import com.patient.reservation.domain.user.service.UserDomainService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Slf4j
@Scope("prototype")
@Component
public class DeleteUserCommand extends BaseTransactionalCommand<Void> {

    @Autowired
    private UserDomainService userDomainService;

    private final User user;

    public DeleteUserCommand(User user){
        this.user = user;
    }

    @Override
    public Void doExecute(){
        log.info("Deleting {}...", user);

        userDomainService.delete(user);

        log.info("{} deleted.", user);

        return null;
    }

}
