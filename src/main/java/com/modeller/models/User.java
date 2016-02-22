package com.modeller.models;

import com.modeller.models.api.Role;
import com.modeller.models.api.SocialMediaService;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by Gadzzzz on 11.02.2016.
 */
public class User {
	@Getter @Setter
	private Long id;
	@Getter @Setter
	private String email;
	@Getter @Setter
	private String firstName;
	@Getter @Setter
	private String lastName;
	@Getter @Setter
	private String password;
	@Getter @Setter
	private Role role;
	@Getter @Setter
	private SocialMediaService signInProvider;
	@Getter @Setter
	private String userName;

	public User() {

	}

}
