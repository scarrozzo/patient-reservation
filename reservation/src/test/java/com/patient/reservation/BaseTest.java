package com.patient.reservation;

import com.patient.reservation.domain.user.model.User;
import com.patient.reservation.security.UserDetailsImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import static org.mockito.Mockito.when;

public abstract class BaseTest {
    @Mock
    Authentication auth;

    @BeforeEach
    protected void initSecurityContext() {
        User doctor = new User();
        doctor.setId(1L);
        doctor.setIsDoctor(Boolean.TRUE);
        doctor.setUid("1440997d-5d19-3b65-b517-05029bb7362e");
        when(auth.getPrincipal()).thenReturn(UserDetailsImpl.build(doctor));
        SecurityContextHolder.getContext().setAuthentication(auth);
    }

    @AfterEach
    protected void clearSecurityContext() {
        SecurityContextHolder.clearContext();
    }
}
