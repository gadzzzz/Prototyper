package com.modeller.dao.impl;

import com.modeller.dao.api.UserDao;
import com.modeller.models.User;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * Created by Gadzzzz on 11.02.2016.
 */
public class UserDaoImpl implements UserDao {

	private static final Logger log = Logger.getLogger(UserDaoImpl.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public void create(String providerId,String providerUserId) {
		jdbcTemplate.update(CREATE,providerId,providerUserId);
	}

	public boolean exist(String providerId, String providerUserId) {
		boolean exist = jdbcTemplate.queryForObject(
			EXIST,
			new Object[]{providerId,providerUserId},
			Integer.class) != 0;
		return exist;
	}

	public long getId(String providerId, String providerUserId){
		return jdbcTemplate.queryForObject(GET_ID,new Object[]{providerId,providerUserId},Long.class);
	}

	public int getStatistic() {
		return jdbcTemplate.queryForObject(
			STATISTIC,
			Integer.class
		);
	}
}
