package com.modeller.dao.impl;

import com.modeller.dao.api.PageDao;
import com.modeller.models.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * Created by Gadzzzz on 25.02.2016.
 */
public class PageDaoImpl implements PageDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public void save(Page page) {
		jdbcTemplate.update(SAVE,page.getPrototypeId(),page.getJson());
	}

	public Page load() {
		return null;
	}
}
