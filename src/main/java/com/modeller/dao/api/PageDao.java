package com.modeller.dao.api;

import com.modeller.models.Page;

import java.util.List;

/**
 * Created by Gadzzzz on 25.02.2016.
 */
public interface PageDao {
	void save(Page page);

	List<Page> load(int prototypeId);

	String SAVE = "INSERT INTO PAGES(prototypeid,doc) values (?,?)";

	String LOAD = "SELECT * FROM PAGES WHERE PROTOTYPEID = ?";
}
