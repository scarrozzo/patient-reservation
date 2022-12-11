package com.patient.reservation.command.user;

import com.patient.reservation.BaseTest;
import com.patient.reservation.domain.user.dto.PostUserDto;
import com.patient.reservation.domain.user.model.PatientIdentifierType;
import com.patient.reservation.domain.user.model.RoleType;
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
import java.util.Set;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class CreatePatientCommandTest extends BaseTest {

    @InjectMocks
    private CreatePatientCommand command;

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

    @DisplayName("Test that it is impossible to create a patient with an invalid role")
    @Test
    void testCreatePatientWithInvalidRole() {
        PostUserDto patient = generateValidPatient();
        patient.setRoles(Set.of(RoleType.WRITER));

        ReflectionTestUtils.setField(command, "dto", patient);
        Assertions.assertThrows(IllegalArgumentException.class, command::doExecute);
    }

    @DisplayName("Tests that it is impossible to create a patient without the patient flag enabled")
    @Test
    void testCreatePatientNoPatientFlag() {
        PostUserDto patient = generateValidPatient();
        patient.setIsPatient(Boolean.FALSE);
        ReflectionTestUtils.setField(command, "dto", patient);
        Assertions.assertThrows(PermissionDeniedException.class, command::doExecute);
    }

    @DisplayName("Tests that it is impossible to set the doctor flag (doctor creation) to true on new patient creation")
    @Test
    void testCreatePatientWithDoctorFlagTrue() {
        PostUserDto patient = generateValidPatient();
        patient.setIsDoctor(Boolean.TRUE);
        ReflectionTestUtils.setField(command, "dto", patient);
        Assertions.assertThrows(PermissionDeniedException.class, command::doExecute);
    }

    @DisplayName("Tests that it is impossible to associate a patient with a doctor other than the logged-in user")
    @Test
    void testCreatePatientAssignedToAnotherDoctor() {
        PostUserDto patient = generateValidPatient();
        patient.setDoctorUid("123");
        ReflectionTestUtils.setField(command, "dto", patient);
        Assertions.assertThrows(PermissionDeniedException.class, command::doExecute);
    }

}
