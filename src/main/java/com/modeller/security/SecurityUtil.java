package com.modeller.security;

import com.modeller.models.ExampleUserDetails;
import com.modeller.models.User;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * Created by Gadzzzz on 11.02.2016.
 */
public class SecurityUtil {
	public static void logInUser(User user) {
		ExampleUserDetails userDetails = ExampleUserDetails.getBuilder()
			.firstName(user.getFirstName())
			.id(user.getId())
			.lastName(user.getLastName())
			.password(user.getPassword())
			.role(user.getRole())
			.username(user.getUserName())
			.socialSignInProvider(user.getSignInProvider())
			.build();

		Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(authentication);
	}
}
