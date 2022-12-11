package com.patient.reservation.command.visit;

import com.patient.reservation.BaseTest;
import com.patient.reservation.command.visit.util.VisitValidator;
import com.patient.reservation.domain.visit.dto.PatchVisitDto;
import com.patient.reservation.domain.visit.model.Visit;
import com.patient.reservation.domain.visit.model.VisitReason;
import com.patient.reservation.domain.visit.model.VisitType;
import com.patient.reservation.domain.visit.service.VisitDomainService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.data.domain.PageImpl;
import org.springframework.test.util.ReflectionTestUtils;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class UpdateVisitCommandTest extends BaseTest {

    @Mock
    private VisitDomainService visitDomainService;

    @InjectMocks
    private UpdateVisitCommand command;

    private PatchVisitDto generateValidVisit(){
        return PatchVisitDto.builder()
                .date(Optional.of(ZonedDateTime.now().plusHours(5).plusMinutes(43)))
                .visitType(Optional.of(VisitType.HOME))
                .visitReason(Optional.of(VisitReason.FIRST_VISIT))
                .patientUid(Optional.of(UUID.randomUUID().toString()))
                .build();
    }

    @DisplayName("Test that it is impossible to update a visit in the past")
    @Test
    void testUpdateVisitInThePast() {
        PatchVisitDto dto = generateValidVisit();
        dto.setDate(Optional.of(ZonedDateTime.now().minusSeconds(1)));

        ReflectionTestUtils.setField(command, "dto", dto);
        ReflectionTestUtils.setField(command, "visitValidator", new VisitValidator());
        Assertions.assertThrows(IllegalArgumentException.class, command::doExecute);
    }

    @DisplayName("Tests that it is impossible to update one visit overlapping the others")
    @Test
    void testUpdateOverlappedVisit() {
        PatchVisitDto dto = generateValidVisit();
        dto.setDate(Optional.of(ZonedDateTime.now().plusMinutes(1)));

        Visit existingVisit = new Visit();
        existingVisit.setUid("54480cb8-49f4-4d88-9473-1f6e5fc1e963");
        when(visitDomainService.getVisits(any(), any())).thenReturn(new PageImpl<>(List.of(existingVisit)));
        VisitValidator visitValidator = new VisitValidator();
        ReflectionTestUtils.setField(visitValidator, "visitDomainService", visitDomainService);

        Visit visitToUpdate = new Visit();
        visitToUpdate.setUid("77cb4cf4-b53b-41aa-b34f-88e3195ca249");
        ReflectionTestUtils.setField(command, "visitValidator", visitValidator);
        ReflectionTestUtils.setField(command, "dto", dto);
        ReflectionTestUtils.setField(command, "visit", visitToUpdate);
        Assertions.assertThrows(IllegalArgumentException.class, command::doExecute);
    }
}
