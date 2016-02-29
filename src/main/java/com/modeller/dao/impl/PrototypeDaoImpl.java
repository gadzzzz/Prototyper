package com.modeller.dao.impl;

import com.modeller.dao.api.PrototypeDao;
import com.modeller.models.Prototype;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Gadzzzz on 24.02.2016.
 */
public class PrototypeDaoImpl implements PrototypeDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public int save(Prototype prototype) {

		jdbcTemplate.update(
			SAVE,
			prototype.getUserId(),
			prototype.getUpdateDate(),
			prototype.getName());
		int prototypeId = jdbcTemplate.queryForObject(
			PROTOTYPE_ID,
			Integer.class);
		return prototypeId-1;
	}

	public Prototype load(int id) {
		return jdbcTemplate.queryForObject(LOAD, new Object[]{id}, new RowMapper<Prototype>() {
			public Prototype mapRow(ResultSet resultSet, int i) throws SQLException {
				Prototype prototype = new Prototype(
					resultSet.getInt("PROTOTYPEID"),
					resultSet.getLong("USERID"),
					resultSet.getDate("UPDATEDATE"),
					resultSet.getString("NAME"),
					null);
				return prototype;
			}
		});
	}

	public int currentId() {
		return 0;
	}
}
