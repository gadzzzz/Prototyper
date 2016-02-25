package com.modeller.dao.impl;

import com.modeller.dao.api.PrototypeDao;
import com.modeller.models.Prototype;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * Created by Gadzzzz on 24.02.2016.
 */
public class PrototypeDaoImpl implements PrototypeDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public int save(Prototype prototype) {
		jdbcTemplate.update(SAVE,prototype.getUserId(),prototype.getUpdateDate());
		int prototypeId = jdbcTemplate.queryForObject(PROTOTYPE_ID,Integer.class);
		return prototypeId-1;
	}

	public Prototype load() {
		return null;
	}
}
