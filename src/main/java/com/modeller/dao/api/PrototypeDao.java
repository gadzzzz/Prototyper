package com.modeller.dao.api;

import com.modeller.models.Prototype;

/**
 * Created by Gadzzzz on 24.02.2016.
 */
public interface PrototypeDao {

	void save(Prototype prototype);

	Prototype load();

	String SAVE = "INSERT INTO PAGES(doc) values (?)";
}
