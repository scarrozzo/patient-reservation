package com.patient.reservation.controller.auth;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class JwtResponse {
    private String username;
    private List<String> roles;
    private String jwtToken;
}
