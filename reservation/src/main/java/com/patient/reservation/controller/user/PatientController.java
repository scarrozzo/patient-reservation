package com.patient.reservation.controller.user;

import com.patient.reservation.controller.PathConfig;
import com.patient.reservation.controller.user.assembler.UserRepresentationModelAssembler;
import com.patient.reservation.controller.user.representation.UserRepresentationModel;
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

import static com.patient.reservation.security.Authorities.ROLE_ADMIN;
import static com.patient.reservation.security.Authorities.ROLE_DOCTOR;
import static org.springframework.http.ResponseEntity.ok;

@Slf4j
@RestController
@Tag(name = "Users API")
@RequestMapping(path = PathConfig.PATIENTS)
@PreAuthorize(ROLE_DOCTOR)
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
    public ResponseEntity<PagedModel<UserRepresentationModel>> getUsers(HttpServletRequest request, HttpServletResponse response, Pageable pageable) {
        Page<User> users = userService.getPatients(pageable);
        return ok().body(userPagedResourcesAssembler.toModel(users, userRepresentationModelAssembler));
    }
}
