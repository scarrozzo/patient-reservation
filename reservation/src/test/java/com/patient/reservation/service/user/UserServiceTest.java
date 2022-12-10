package com.patient.reservation.service.user;

import com.patient.reservation.domain.user.dto.PostUserDto;
import com.patient.reservation.domain.user.model.PatientIdentifierType;
import com.patient.reservation.domain.user.model.RoleType;
import com.patient.reservation.domain.user.model.User;
import com.patient.reservation.exception.PermissionDeniedException;
import com.patient.reservation.security.UserDetailsImpl;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.time.LocalDate;
import java.util.Set;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class UserServiceTest {

    @Spy
    @InjectMocks
    private UserService userService;

    @Mock
    private Authentication auth;

    @BeforeEach
    public void initSecurityContext() {
        User doctor = new User();
        doctor.setId(1L);
        doctor.setIsDoctor(Boolean.TRUE);
        doctor.setUid("1440997d-5d19-3b65-b517-05029bb7362e");
        when(auth.getPrincipal()).thenReturn(UserDetailsImpl.build(doctor));
        SecurityContextHolder.getContext().setAuthentication(auth);
    }

    @AfterEach
    public void clearSecurityContext() {
        SecurityContextHolder.clearContext();
    }

    private PostUserDto generateValidPatient(){
        return PostUserDto.builder()
                .username("patient1")
                .password("Password123?")
                .firstName("Mario")
                .lastName("Rossi")
                .patientIdentifier("500-80-1118")
                .patientIdentifierType(PatientIdentifierType.SOCIAL_SECURITY_NUMBER)
                .isDoctor(Boolean.FALSE)
                .isPatient(Boolean.TRUE)
                .dateOfBirth(LocalDate.of(1990, 10,10))
                .loginEnabled(Boolean.FALSE)
                .roles(Set.of(RoleType.READER))
                .build();
    }

    @DisplayName("Test create patient with an invalid role")
    @Test
    void testCreatePatientWrongRole() {
        PostUserDto patientWrongRole = generateValidPatient();
        patientWrongRole.setRoles(Set.of(RoleType.WRITER));

        doReturn(new User()).when(userService).createUser(any());
        Assertions.assertThrows(IllegalArgumentException.class, () -> userService.createPatient(patientWrongRole));
    }

    @DisplayName("Test create patient that is not a patient")
    @Test
    void testCreatePatientNoPatientFlag() {
        PostUserDto patientWrongRole = generateValidPatient();
        patientWrongRole.setIsPatient(Boolean.FALSE);

        doReturn(new User()).when(userService).createUser(any());
        Assertions.assertThrows(PermissionDeniedException.class, () -> userService.createPatient(patientWrongRole));
    }

    @DisplayName("Test create patient that is a doctor")
    @Test
    void testCreatePatientWithDoctorFlagTrue() {
        PostUserDto patientWrongRole = generateValidPatient();
        patientWrongRole.setIsDoctor(Boolean.TRUE);

        doReturn(new User()).when(userService).createUser(any());
        Assertions.assertThrows(PermissionDeniedException.class, () -> userService.createPatient(patientWrongRole));
    }

    @DisplayName("Test create patient assigned to another doctor")
    @Test
    void testCreatePatientAssignedToAnotherDoctor() {
        PostUserDto patientWrongRole = generateValidPatient();
        patientWrongRole.setDoctorUid("123");

        doReturn(new User()).when(userService).createUser(any());
        Assertions.assertThrows(PermissionDeniedException.class, () -> userService.createPatient(patientWrongRole));
    }

}
