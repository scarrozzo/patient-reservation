package com.patient.reservation.domain.user.factory;

import com.patient.reservation.domain.user.dto.PostUserDto;
import com.patient.reservation.domain.user.model.Role;
import com.patient.reservation.domain.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class UserFactory {

    @Autowired
    private UserDtoMapper userDtoMapper;

    public User createUser(PostUserDto dto, User doctor, Set<Role> roles){
        User user = new User();
        user = userDtoMapper.map(user, dto);

        if(doctor != null) {
            user.setDoctorId(doctor.getId());
        }

        user.setRoles(roles);

        return user;
    }

}
