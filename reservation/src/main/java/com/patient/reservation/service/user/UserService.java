package com.patient.reservation.service.user;

import com.patient.reservation.domain.user.model.User;
import com.patient.reservation.domain.user.service.UserDomainService;
import lombok.extern.slf4j.Slf4j;
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

    public Page<User> getUsers(Pageable pageable){
        return userDomainService.getUsers(pageable);
    }

    public User getUser(String uid){
        return userDomainService.getUser(uid);
    }

}
