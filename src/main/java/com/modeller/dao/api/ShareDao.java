package com.modeller.dao.api;

import com.modeller.models.Share;

import java.util.List;

/**
 * Created by Gadzzzz on 22.03.2016.
 */
public interface ShareDao {

	void save(Share share);

	List<Share> loadAll(long userId);

	void delete(int id);

	void delete(String link);

	boolean exist(int userId, String link);

	String SAVE = "INSERT INTO SHARES(userid,prototypelink) values(?,?)";

	String LOADALL = "SELECT * FROM SHARES WHERE userid = ?";

	String EXIST = "SELECT COUNT(*) FROM SHARES WHERE userid = ? and prototypelink = ?";

	String DELETE = "DELETE FROM SHARES WHERE prototypelink = ?";

	String DELETE_BY_ID = "DELETE FROM SHARES WHERE shareid = ?";
}
