package com.modeller.controllers;

import com.modeller.dao.api.UserDao;
import com.modeller.models.ExampleUserDetails;
import com.modeller.security.SecurityUtil;
import com.modeller.models.User;
import com.modeller.models.api.Role;
import com.modeller.models.api.SocialMediaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionKey;
import org.springframework.social.connect.UserProfile;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Gadzzzz on 10.02.2016.
 */
@Controller
public class Hello {

	@Autowired
	@Qualifier("userDaoImpl")
	private UserDao userDao;

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String showLoginPage(Authentication authentication) {
		if(authentication!=null)
			return "redirect:/logged";
		return "login";
	}

	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public String redirectRequestToRegistrationPage(WebRequest request) {
		Connection<?> connection = ProviderSignInUtils.getConnection(request);
		String providerId = connection.getKey().getProviderId().toUpperCase();
		String providerUserId = connection.getKey().getProviderUserId();
		if(!userDao.exist(providerId,providerUserId))
			userDao.create(providerId,providerUserId);
		User logged = makeLogin(connection);
		logged.setId(userDao.getId(providerId,providerUserId));
		SecurityUtil.logInUser(logged);
		return "redirect:/logged";
	}

	@RequestMapping(value = "/logged", method = RequestMethod.GET)
	public String logged(Authentication authentication, HttpServletRequest request){
		ExampleUserDetails user = (ExampleUserDetails) authentication.getPrincipal();
		request.getSession().setAttribute("userinfo",user.getUsername());
		request.getSession().setAttribute("provider",user.getSocialSignInProvider());
		return "modeller";
	}

	private User makeLogin(Connection<?> connection) {
		User user = new User();
		if (connection != null) {
			UserProfile socialMediaProfile = connection.fetchUserProfile();
			user.setEmail(socialMediaProfile.getEmail());
			user.setFirstName(socialMediaProfile.getFirstName());
			user.setLastName(socialMediaProfile.getLastName());
			user.setRole(Role.ROLE_USER);
			user.setUserName(socialMediaProfile.getName());
			ConnectionKey providerKey = connection.getKey();
			user.setSignInProvider(SocialMediaService.valueOf(providerKey.getProviderId().toUpperCase()));
		}
		return user;
	}
}
