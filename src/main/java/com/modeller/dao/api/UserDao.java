package com.modeller.dao.api;

import com.modeller.models.User;

/**
 * Created by Gadzzzz on 11.02.2016.
 */
public interface UserDao {

	void create(String providerId,String providerUserId);

	boolean exist(String providerId,String providerUserId);

	String EXIST = "SELECT COUNT(*) FROM USERCONNECTION WHERE PROVIDERID = ? AND PROVIDERUSERID = ?";

	String CREATE = "INSERT INTO USERCONNECTION(PROVIDERID,PROVIDERUSERID) values (?,?)";
}
