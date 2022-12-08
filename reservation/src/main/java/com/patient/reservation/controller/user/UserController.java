package com.patient.reservation.controller.user;

import com.patient.reservation.controller.PathConfig;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.patient.reservation.controller.PathConfig.UID_TEMPLATE;
import static org.springframework.http.ResponseEntity.ok;

@Slf4j
@RestController
@Tag(name = "Users API")
@RequestMapping(path = PathConfig.USERS)
public class UserController {

    @Operation(summary = "Get a paginated list of users")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "The list of users",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = String.class))})})
    @GetMapping
    public ResponseEntity<String> getUsers() {
        return ok().body("Test");
    }

    @Operation(summary = "Get a user by uid")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the user",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = String.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid uid supplied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "User not found",
                    content = @Content)})
    @GetMapping(UID_TEMPLATE)
    public ResponseEntity<?> getUserByUid() {
        return ok().body("Test2");
    }
}
