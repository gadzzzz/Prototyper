package com.modeller.dao.impl;

import com.modeller.dao.api.PrototypeDao;
import com.modeller.models.Prototype;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

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
			prototype.getName(),
			prototype.getLink());
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
					resultSet.getString("PROTOTYPELINK"),
					null);
				return prototype;
			}
		});
	}

	public List<Prototype> loadAll(long userId) {
		return jdbcTemplate.query(LOADALL, new Object[]{userId}, new RowMapper<Prototype>() {
			public Prototype mapRow(ResultSet resultSet, int i) throws SQLException {
				Prototype prototype = new Prototype(
					resultSet.getInt("PROTOTYPEID"),
					resultSet.getLong("USERID"),
					resultSet.getDate("UPDATEDATE"),
					resultSet.getString("NAME"),
					resultSet.getString("PROTOTYPELINK"),
					null);
				return prototype;
			}
		});
	}

	public void update(Prototype prototype) {
		jdbcTemplate.update(
			UPDATE,
			prototype.getName(),
			prototype.getUpdateDate(),
			prototype.getId());
	}

	public void delete(int prototypeId) {
		jdbcTemplate.update(
			DELETE,
			prototypeId
		);
	}

	public boolean exist(String name, long userId) {
		boolean exist = jdbcTemplate.queryForObject(
			EXIST,
			new Object[]{name,userId},
			Integer.class) != 0;
		return exist;
	}

	public int lodaByName(String name) {
		return jdbcTemplate.queryForObject(
			LOAD_BY_NAME,
			new Object[]{name},
			Integer.class);
	}

	public Prototype loadByLink(String link) {
		return jdbcTemplate.queryForObject(LOAD_BY_LINK, new Object[]{link}, new RowMapper<Prototype>() {
			public Prototype mapRow(ResultSet resultSet, int i) throws SQLException {
				Prototype prototype = new Prototype(
					resultSet.getInt("PROTOTYPEID"),
					resultSet.getLong("USERID"),
					resultSet.getDate("UPDATEDATE"),
					resultSet.getString("NAME"),
					resultSet.getString("PROTOTYPELINK"),
					null);
				return prototype;
			}
		});
	}

	public void updateName(Prototype prototype) {
		jdbcTemplate.update(
			UPDATE_NAME,
			prototype.getName(),
			prototype.getId());
	}

	public int getStatistic() {
		return jdbcTemplate.queryForObject(
			STATISTIC,
			Integer.class
		);
	}

}
