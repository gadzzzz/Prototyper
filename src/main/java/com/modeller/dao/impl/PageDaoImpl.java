package com.modeller.dao.impl;

import com.modeller.dao.api.PageDao;
import com.modeller.models.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Gadzzzz on 25.02.2016.
 */
public class PageDaoImpl implements PageDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public void save(Page page) {
		jdbcTemplate.update(SAVE,page.getPrototypeId(),page.getJson());
	}

	public List<Page> load(int prototypeId) {
		return jdbcTemplate.query(LOAD, new Object[]{prototypeId}, new RowMapper<Page>() {
			public Page mapRow(ResultSet resultSet, int i) throws SQLException {
				Page page = new Page(
					resultSet.getInt("PAGEID"),
					resultSet.getInt("PROTOTYPEID"),
					resultSet.getString("DOC"));
				return page;
			}
		});
	}
}
