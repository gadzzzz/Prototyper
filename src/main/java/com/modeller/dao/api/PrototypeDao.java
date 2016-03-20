package com.modeller.dao.api;

import com.modeller.models.Prototype;

import java.util.List;

/**
 * Created by Gadzzzz on 24.02.2016.
 */
public interface PrototypeDao {

	int save(Prototype prototype);

	Prototype load(int id);

	List<Prototype> loadAll(long userId);

	void update(Prototype prototype);

	void delete(int prototypeId);

	boolean exist(String name);

	int lodaByName(String name);

	int getStatistic();

	String SAVE = "INSERT INTO PROTOTYPES(userid,updatedate,name) values (?,?,?)";

	String PROTOTYPE_ID = "SELECT prototype_id.nextval from dual";

	String LOAD = "SELECT * FROM PROTOTYPES WHERE PROTOTYPEID = ?";

	String LOADALL = "SELECT * FROM PROTOTYPES WHERE USERID = ?";

	String UPDATE = "UPDATE PROTOTYPES SET name = ?, updatedate = ? WHERE PROTOTYPEID = ?";

	String DELETE = "DELETE FROM PROTOTYPES WHERE PROTOTYPEID = ?";

	String EXIST = "SELECT COUNT(*) FROM PROTOTYPES WHERE name = ?";

	String LOAD_BY_NAME = "SELECT PROTOTYPEID FROM PROTOTYPES WHERE name = ?";

	String STATISTIC = "SELECT count(*) FROM PROTOTYPES";
}
