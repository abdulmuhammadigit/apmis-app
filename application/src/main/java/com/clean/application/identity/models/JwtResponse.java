package com.clean.application.identity.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class JwtResponse {
	private String token;
	@Builder.Default
	private String type = "Bearer";
	private Long id;
	private String username;
	private String fullName;
	private String email;
	private boolean passwordExpired;
}
