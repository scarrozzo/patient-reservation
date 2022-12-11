package com.patient.reservation.domain.visit.factory;

import com.patient.reservation.BaseTest;
import com.patient.reservation.domain.user.model.User;
import com.patient.reservation.domain.visit.dto.PostVisitDto;
import com.patient.reservation.domain.visit.model.Visit;
import com.patient.reservation.domain.visit.model.VisitReason;
import com.patient.reservation.domain.visit.model.VisitType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.time.ZonedDateTime;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
public class VisitFactoryTest extends BaseTest {

    @InjectMocks
    private VisitFactory visitFactory;

    private PostVisitDto generateValidVisit(){
        return PostVisitDto.builder()
                .date(ZonedDateTime.now().plusHours(2).plusMinutes(30))
                .visitType(VisitType.DOCTOR_OFFICE)
                .visitReason(VisitReason.RECURRING_VISIT)
                .familyHistory("This is a note")
                .patientUid(UUID.randomUUID().toString())
                .build();
    }

    @DisplayName("Tests the creation of a visit entity successfully ")
    @Test
    void testCreateVisit(){
        PostVisitDto dto = generateValidVisit();
        User patient = new User();
        patient.setId(10L);

        ReflectionTestUtils.setField(visitFactory, "visitDtoMapper", new VisitDtoMapper());

        Visit visit = visitFactory.createVisit(dto, patient);
        Assertions.assertEquals(dto.getDate(), visit.getDate());
        Assertions.assertEquals(dto.getVisitType(), visit.getVisitType());
        Assertions.assertEquals(dto.getVisitReason(), visit.getVisitReason());
        Assertions.assertEquals(dto.getFamilyHistory(), visit.getFamilyHistory());
        Assertions.assertEquals(patient.getId(), visit.getPatientId());
    }
}
