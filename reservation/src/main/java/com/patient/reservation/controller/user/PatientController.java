package com.patient.reservation.controller.user;

import com.patient.reservation.controller.PathConfig;
import com.patient.reservation.controller.user.assembler.UserRepresentationModelAssembler;
import com.patient.reservation.controller.user.representation.UserRepresentationModel;
import com.patient.reservation.domain.user.dto.PostUserDto;
import com.patient.reservation.domain.user.model.User;
import com.patient.reservation.service.user.UserService;
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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

import static com.patient.reservation.security.Authorities.ROLE_WRITER;
import static org.springframework.http.ResponseEntity.created;
import static org.springframework.http.ResponseEntity.ok;

@Slf4j
@RestController
@Tag(name = "Users API")
@RequestMapping(path = PathConfig.PATIENTS)
@PreAuthorize(ROLE_WRITER)
public class PatientController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepresentationModelAssembler userRepresentationModelAssembler;

    @Autowired
    private PagedResourcesAssembler<User> userPagedResourcesAssembler;

    @Operation(summary = "Get a paginated list of patients")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "The list of users",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = UserRepresentationModel.class))})})
    @GetMapping
    public ResponseEntity<PagedModel<UserRepresentationModel>> getPatients(HttpServletRequest request, HttpServletResponse response,
                                                                           @Valid PatientSearchParameters searchParameters, Pageable pageable) {
        Page<User> users = userService.getPatients(searchParameters, pageable);
        return ok().body(userPagedResourcesAssembler.toModel(users, userRepresentationModelAssembler));
    }

    @Operation(summary = "Create a patient")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Patient created", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = UserRepresentationModel.class))})
    })
    @PostMapping
    public ResponseEntity<UserRepresentationModel> createPatient(HttpServletRequest request, HttpServletResponse response, @RequestBody @Valid PostUserDto postUserDto){
        User user = userService.createPatient(postUserDto);

        final URI location = ServletUriComponentsBuilder
                .fromCurrentServletMapping().path(PathConfig.USERS + "/{uid}").build()
                .expand(user.getUid()).toUri();

        return created(location).body(userRepresentationModelAssembler.toModel(user));
    }

}
