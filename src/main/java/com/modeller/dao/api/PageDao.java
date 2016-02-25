package com.modeller.dao.api;

import com.modeller.models.Page;

/**
 * Created by Gadzzzz on 25.02.2016.
 */
public interface PageDao {
	public void save(Page page);

	public Page load();

	String SAVE = "INSERT INTO PAGES(prototypeid,doc) values (?,?)";
}
