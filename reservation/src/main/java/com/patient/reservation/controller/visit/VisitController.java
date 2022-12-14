package com.patient.reservation.controller.visit;

import com.patient.reservation.controller.PathConfig;
import com.patient.reservation.controller.visit.assembler.VisitRepresentationModelAssembler;
import com.patient.reservation.controller.visit.representation.VisitRepresentationModel;
import com.patient.reservation.domain.visit.dto.PatchVisitDto;
import com.patient.reservation.domain.visit.dto.PostVisitDto;
import com.patient.reservation.domain.visit.model.Visit;
import com.patient.reservation.service.visit.VisitService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

import static com.patient.reservation.controller.PathConfig.UID_TEMPLATE;
import static com.patient.reservation.security.Authorities.ROLE_WRITER;
import static org.springframework.http.ResponseEntity.*;

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

    @Operation(summary = "Get a paginated list of visits")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "The list of visits",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = VisitRepresentationModel.class))})})
    @GetMapping
    public ResponseEntity<PagedModel<VisitRepresentationModel>> getVisits(HttpServletRequest request, HttpServletResponse response,
                                                                           @Valid VisitSearchParameters searchParameters, Pageable pageable) {
        Page<Visit> visits = visitService.getVisits(searchParameters, pageable);
        return ok().body(visitPagedResourcesAssembler.toModel(visits, visitRepresentationModelAssembler));
    }

    @Operation(summary = "Get a visit by uid")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the visit", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = VisitRepresentationModel.class))}),
            @ApiResponse(responseCode = "404", description = "Visit not found", content = @Content)})
    @GetMapping(UID_TEMPLATE)
    public ResponseEntity<VisitRepresentationModel> getVisit(HttpServletRequest request, HttpServletResponse response,
                                                           @Parameter(description = "The identifier of the Visit.", example = "80b9ffcf-f374-47b4-8774-bfbaa2c64ebe")
                                                           @PathVariable String uid) {
        Visit visit = visitService.getVisit(uid);
        return ok().body(visitRepresentationModelAssembler.toModel(visit));
    }

    @Operation(summary = "Create a visit")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Visit created", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = VisitRepresentationModel.class))})
    })
    @PostMapping
    public ResponseEntity<VisitRepresentationModel> createVisit(HttpServletRequest request, HttpServletResponse response, @RequestBody @Valid PostVisitDto postVisitDto){
        Visit visit = visitService.createVisit(postVisitDto);

        final URI location = ServletUriComponentsBuilder
                .fromCurrentServletMapping().path(PathConfig.USERS + "/{uid}").build()
                .expand(visit.getUid()).toUri();

        return created(location).body(visitRepresentationModelAssembler.toModel(visit));
    }

    @Operation(summary = "Update a visit")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Visit updated", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = VisitRepresentationModel.class))})
    })
    @PatchMapping(UID_TEMPLATE)
    public ResponseEntity<VisitRepresentationModel> updateVisit(HttpServletRequest request, HttpServletResponse response,
                                                                 @Parameter(description = "The identifier of the Visit.", example = "80b9ffcf-f374-47b4-8774-bfbaa2c64ebe") @PathVariable String uid,
                                                                 @RequestBody @Valid PatchVisitDto patchVisitDto){
        Visit visit = visitService.updateVisit(uid, patchVisitDto);
        return ok().body(visitRepresentationModelAssembler.toModel(visit));
    }

    @Operation(summary = "Delete a visit")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Visit deleted", content = @Content)
    })
    @DeleteMapping(UID_TEMPLATE)
    public ResponseEntity<Void> deleteVisit(@Parameter(description = "The identifier of the Visit.", example = "80b9ffcf-f374-47b4-8774-bfbaa2c64ebe")
                                           @PathVariable String uid){
        visitService.deleteVisit(uid);
        return noContent().build();
    }
}
