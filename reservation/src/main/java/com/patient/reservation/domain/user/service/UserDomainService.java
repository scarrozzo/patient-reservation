package com.patient.reservation.domain.user.service;


import com.patient.reservation.domain.user.model.User;
import com.patient.reservation.domain.user.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserDomainService {

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

}
