package com.patient.reservation.service.user;

import com.patient.reservation.command.user.CreateUserCommand;
import com.patient.reservation.command.user.DeleteUserCommand;
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

    public Page<User> getUsers(Pageable pageable){
        return userDomainService.getUsers(pageable);
    }

    public User getUser(String uid){
        return userDomainService.getUser(uid);
    }

    public Page<User> getPatients(Pageable pageable){
        return userDomainService.getPatients(pageable);
    }

    public User createUser(PostUserDto postUserDto){
        CreateUserCommand command = beanFactory.getBean(CreateUserCommand.class, postUserDto);
        return command.execute();
    }

    public void deleteUser(String uid){
        User user = userDomainService.getUser(uid);
        DeleteUserCommand command = beanFactory.getBean(DeleteUserCommand.class, user);
        command.execute();
    }

}
