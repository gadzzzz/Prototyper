package com.modeller.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.social.UserIdSource;
import org.springframework.social.config.annotation.ConnectionFactoryConfigurer;
import org.springframework.social.config.annotation.EnableSocial;
import org.springframework.social.config.annotation.SocialConfigurer;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.jdbc.JdbcUsersConnectionRepository;
import org.springframework.social.connect.web.ConnectController;
import org.springframework.social.facebook.connect.FacebookConnectionFactory;
import org.springframework.social.google.connect.GoogleConnectionFactory;
import org.springframework.social.security.AuthenticationNameUserIdSource;
import org.springframework.social.vkontakte.connect.VKontakteConnectionFactory;

import javax.sql.DataSource;

/**
 * Created by Gadzzzz on 10.02.2016.
 */
@Configuration
@EnableSocial
public class SocialConfig implements SocialConfigurer {

	@Autowired
	private DataSource dataSource;

	public void addConnectionFactories(ConnectionFactoryConfigurer cfConfig, Environment env) {
		cfConfig.addConnectionFactory(new FacebookConnectionFactory(
			"1716509515237050",
			"408b1765122d7e0f88354df7588fa3a3"
		));
		cfConfig.addConnectionFactory(new GoogleConnectionFactory(
			"626461146394-rcmi9hri266kr0d4q8psvr2bgfujjrkm.apps.googleusercontent.com",
			"Ny5PSlKZOeRcHkKdxEmZ3CYd"
		));
		cfConfig.addConnectionFactory(new VKontakteConnectionFactory(
			"5311498",
			"jeFi25IgFdcad3Uuj34y"
		));
	}

	public UserIdSource getUserIdSource() {
		return new AuthenticationNameUserIdSource();
	}

	public UsersConnectionRepository getUsersConnectionRepository(ConnectionFactoryLocator connectionFactoryLocator) {
		return new JdbcUsersConnectionRepository(
			dataSource,
			connectionFactoryLocator,
			Encryptors.noOpText()
		);
	}

	@Bean
	public ConnectController connectController(ConnectionFactoryLocator connectionFactoryLocator, ConnectionRepository connectionRepository) {
		return new ConnectController(connectionFactoryLocator, connectionRepository);
	}
}
