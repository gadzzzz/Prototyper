package com.modeller.models;

import com.modeller.models.api.Role;
import com.modeller.models.api.SocialMediaService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.social.security.SocialUser;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class ExampleUserDetails extends SocialUser {
	@Getter @Setter
	private Long id;
	@Getter @Setter
	private String firstName;
	@Getter @Setter
	private String lastName;
	@Getter @Setter
	private Role role;
	@Getter @Setter
	private SocialMediaService socialSignInProvider;

	public static Builder getBuilder() {
		return new Builder();
	}

	public ExampleUserDetails(String username, String password, Collection<? extends GrantedAuthority> authorities) {
		super(username, password, authorities);
	}


	public static class Builder {

		private Long id;

		private String username;

		private String firstName;

		private String lastName;

		private String password;

		private Role role;

		private SocialMediaService socialSignInProvider;

		private Set<GrantedAuthority> authorities;

		public Builder() {
			this.authorities = new HashSet<GrantedAuthority>();
		}

		public Builder firstName(String firstName) {
			this.firstName = firstName;
			return this;
		}

		public Builder id(Long id) {
			this.id = id;
			return this;
		}

		public Builder lastName(String lastName) {
			this.lastName = lastName;
			return this;
		}

		public Builder password(String password) {
			if (password == null) {
				password = "SocialUser";
			}

			this.password = password;
			return this;
		}

		public Builder role(Role role) {
			this.role = role;

			SimpleGrantedAuthority authority = new SimpleGrantedAuthority(role.toString());
			this.authorities.add(authority);

			return this;
		}

		public Builder socialSignInProvider(SocialMediaService socialSignInProvider) {
			this.socialSignInProvider = socialSignInProvider;
			return this;
		}

		public Builder username(String username) {
			this.username = username;
			return this;
		}

		public ExampleUserDetails build() {
			ExampleUserDetails user = new ExampleUserDetails(username, password, authorities);

			user.id = id;
			user.firstName = firstName;
			user.lastName = lastName;
			user.role = role;
			user.socialSignInProvider = socialSignInProvider;

			return user;
		}
	}
}