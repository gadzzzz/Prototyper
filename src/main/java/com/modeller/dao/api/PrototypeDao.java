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

	boolean exist(String name, long userId);

	int lodaByName(String name);

	Prototype loadByLink(String link);

	void updateName(Prototype prototype);

	int getStatistic();

	String SAVE = "INSERT INTO PROTOTYPES(userid,updatedate,name,prototypelink) values (?,?,?,?)";

	String PROTOTYPE_ID = "SELECT prototype_id.nextval from dual";

	String LOAD = "SELECT * FROM PROTOTYPES WHERE PROTOTYPEID = ?";

	String LOADALL = "SELECT * FROM PROTOTYPES WHERE USERID = ?";

	String UPDATE = "UPDATE PROTOTYPES SET name = ?, updatedate = ? WHERE PROTOTYPEID = ?";

	String UPDATE_NAME = "UPDATE PROTOTYPES SET name = ? WHERE PROTOTYPEID = ?";

	String DELETE = "DELETE FROM PROTOTYPES WHERE PROTOTYPEID = ?";

	String EXIST = "SELECT COUNT(*) FROM PROTOTYPES WHERE name = ? and userid = ?";

	String LOAD_BY_NAME = "SELECT PROTOTYPEID FROM PROTOTYPES WHERE name = ?";

	String STATISTIC = "SELECT count(*) FROM PROTOTYPES";

	String LOAD_BY_LINK = "SELECT * FROM PROTOTYPES WHERE prototypelink = ?";
}
