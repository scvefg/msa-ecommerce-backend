package com.hexagonal.user.domain;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {

	private String email;
	private String password;

}