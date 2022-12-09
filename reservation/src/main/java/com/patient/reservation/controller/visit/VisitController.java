package com.patient.reservation.controller.visit;

import com.patient.reservation.controller.PathConfig;
import com.patient.reservation.controller.user.PatientSearchParameters;
import com.patient.reservation.controller.user.representation.UserRepresentationModel;
import com.patient.reservation.controller.visit.assembler.VisitRepresentationModelAssembler;
import com.patient.reservation.domain.user.model.User;
import com.patient.reservation.domain.visit.model.Visit;
import com.patient.reservation.domain.visit.service.VisitDomainService;
import com.patient.reservation.service.visit.VisitService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.patient.reservation.security.Authorities.ROLE_WRITER;
import static org.springframework.http.ResponseEntity.ok;

@Slf4j
@RestController
@Tag(name = "Visits API")
@RequestMapping(path = PathConfig.VISITS)
@PreAuthorize(ROLE_WRITER)
public class VisitController {

    @Autowired
    private VisitService visitService;

    @Autowired
    private VisitRepresentationModelAssembler visitRepresentationModelAssembler;

    @Autowired
    private PagedResourcesAssembler<Visit> visitPagedResourcesAssembler;

    @Operation(summary = "Get a paginated list of patients")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "The list of users",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = UserRepresentationModel.class))})})
    @GetMapping
    public ResponseEntity<PagedModel<UserRepresentationModel>> getPatients(HttpServletRequest request, HttpServletResponse response,
                                                                           @Valid VisitSearchParameters searchParameters, Pageable pageable) {
        // Page<Visit> users = visitService.getVisits(searchParameters, pageable);
        //return ok().body(visitPagedResourcesAssembler.toModel(visits, visitRepresentationModelAssembler));
        // TODO
        return null;
    }

}
