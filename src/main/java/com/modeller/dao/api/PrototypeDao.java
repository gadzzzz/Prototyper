package com.modeller.dao.api;

import com.modeller.models.Prototype;

/**
 * Created by Gadzzzz on 24.02.2016.
 */
public interface PrototypeDao {

	int save(Prototype prototype);

	Prototype load(int id);

	int currentId();

	String SAVE = "INSERT INTO PROTOTYPES(userid,updatedate,name) values (?,?,?)";

	String PROTOTYPE_ID = "SELECT prototype_id.nextval from dual";

	String LOAD = "SELECT * FROM PROTOTYPES WHERE PROTOTYPEID=?";
}
