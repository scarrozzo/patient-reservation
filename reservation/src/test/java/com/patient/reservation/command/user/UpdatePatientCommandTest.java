package com.patient.reservation.command.user;

import com.patient.reservation.BaseTest;
import com.patient.reservation.domain.user.dto.PatchUserDto;
import com.patient.reservation.domain.user.model.PatientIdentifierType;
import com.patient.reservation.domain.user.model.User;
import com.patient.reservation.exception.PermissionDeniedException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.test.util.ReflectionTestUtils;

import java.time.LocalDate;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class UpdatePatientCommandTest extends BaseTest {

    @InjectMocks
    private UpdatePatientCommand command;

    private PatchUserDto generateValidPatient(){
        return PatchUserDto.builder()
                .firstName(Optional.of("Mario"))
                .lastName(Optional.of("Rossi"))
                .patientIdentifier(Optional.of("500-80-1118"))
                .patientIdentifierType(Optional.of(PatientIdentifierType.SOCIAL_SECURITY_NUMBER))
                .dateOfBirth(Optional.of(LocalDate.of(1990, 10,10)))
                .loginEnabled(Optional.of(Boolean.FALSE))
                .build();
    }

    @DisplayName("Tests that it is impossible to update a user that is not a patient")
    @Test
    void testUpdateUserNotPatient() {
        PatchUserDto patient = generateValidPatient();

        User user = new User();
        user.setIsPatient(Boolean.FALSE);

        ReflectionTestUtils.setField(command, "dto", patient);
        ReflectionTestUtils.setField(command, "user", user);
        Assertions.assertThrows(PermissionDeniedException.class, command::doExecute);
    }

    @DisplayName("Tests that it is impossible to update a patient of another doctor")
    @Test
    void testUpdateUserAnotherDoctor() {
        PatchUserDto patient = generateValidPatient();

        User user = new User();
        user.setIsPatient(Boolean.TRUE);
        user.setDoctorId(12345L);

        ReflectionTestUtils.setField(command, "dto", patient);
        ReflectionTestUtils.setField(command, "user", user);
        Assertions.assertThrows(PermissionDeniedException.class, command::doExecute);
    }
}
