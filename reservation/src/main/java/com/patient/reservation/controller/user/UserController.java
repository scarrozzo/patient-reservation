package com.patient.reservation.controller.user;

import com.patient.reservation.controller.PathConfig;
import com.patient.reservation.controller.user.assembler.UserRepresentationModelAssembler;
import com.patient.reservation.controller.user.representation.UserRepresentationModel;
import com.patient.reservation.domain.user.model.User;
import com.patient.reservation.service.user.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.patient.reservation.controller.PathConfig.UID_TEMPLATE;
import static org.springframework.http.ResponseEntity.ok;

@Slf4j
@RestController
@Tag(name = "Users API")
@RequestMapping(path = PathConfig.USERS)
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepresentationModelAssembler userRepresentationModelAssembler;

    @Autowired
    private PagedResourcesAssembler<User> userPagedResourcesAssembler;

    @Operation(summary = "Get a paginated list of users")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "The list of users",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = UserRepresentationModel.class))})})
    @GetMapping
    public ResponseEntity<PagedModel<UserRepresentationModel>> getUsers(HttpServletRequest request, HttpServletResponse response, Pageable pageable) {
        Page<User> users = userService.getUsers(pageable);
        return ok().body(userPagedResourcesAssembler.toModel(users, userRepresentationModelAssembler));
    }

    @Operation(summary = "Get a user by uid")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the user", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = UserRepresentationModel.class))}),
            @ApiResponse(responseCode = "404", description = "User not found", content = @Content)})
    @GetMapping(UID_TEMPLATE)
    public ResponseEntity<UserRepresentationModel> getUser(HttpServletRequest request, HttpServletResponse response,
                                                           @Parameter(description = "The identifier of the User.", example = "80b9ffcf-f374-47b4-8774-bfbaa2c64ebe")
                                                           @PathVariable String uid) {
        User user = userService.getUser(uid);
        return ok().body(userRepresentationModelAssembler.toModel(user));
    }
}
