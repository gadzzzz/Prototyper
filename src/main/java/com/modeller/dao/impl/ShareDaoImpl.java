package com.modeller.dao.impl;

import com.modeller.dao.api.ShareDao;
import com.modeller.models.Share;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Gadzzzz on 22.03.2016.
 */
public class ShareDaoImpl implements ShareDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public void save(Share share) {
		jdbcTemplate.update(
			SAVE,
			share.getUserId(),
			share.getLink());
	}

	public List<Share> loadAll(long userId) {
		return jdbcTemplate.query(LOADALL, new Object[]{userId}, new RowMapper<Share>() {
			public Share mapRow(ResultSet resultSet, int i) throws SQLException {
				Share share = new Share(
					resultSet.getInt("shareid"),
					resultSet.getInt("userid"),
					resultSet.getString("prototypelink")
				);
				return share;
			}
		});
	}

	public void delete(String link) {
		jdbcTemplate.update(
			DELETE,
			link
		);
	}

	public void delete(int id) {
		jdbcTemplate.update(
			DELETE_BY_ID,
			id
		);
	}

	public boolean exist(int userId, String link) {
		boolean exist = jdbcTemplate.queryForObject(
			EXIST,
			new Object[]{userId,link},
			Integer.class) != 0;
		return exist;
	}
}
