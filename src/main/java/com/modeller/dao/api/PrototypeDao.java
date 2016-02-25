package com.modeller.dao.api;

import com.modeller.models.Prototype;

/**
 * Created by Gadzzzz on 24.02.2016.
 */
public interface PrototypeDao {

	int save(Prototype prototype);

	Prototype load();

	String SAVE = "INSERT INTO PROTOTYPES(userid,updatedate) values (?,?)";

	String PROTOTYPE_ID = "SELECT prototype_id.nextval from dual";
}
