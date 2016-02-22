package com.modeller.logic.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.social.security.SocialUser;
import org.springframework.social.security.SocialUserDetails;
import org.springframework.social.security.SocialUserDetailsService;
import org.springframework.stereotype.Service;

/**
 * Created by Gadzzzz on 11.02.2016.
 */
public class SimpleSocialUserDetailsService implements SocialUserDetailsService {

	private UserDetailsService userDetailsService;

	public SimpleSocialUserDetailsService(UserDetailsService userDetailsService) {
		this.userDetailsService = userDetailsService;
	}

	public SocialUserDetails loadUserByUserId(String userId) throws UsernameNotFoundException, DataAccessException {
		UserDetails userDetails = userDetailsService.loadUserByUsername(userId);
		return (SocialUserDetails) userDetails;
	}
}
