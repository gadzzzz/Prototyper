package com.modeller.controllers;

import com.google.gson.Gson;
import com.modeller.dao.api.PageDao;
import com.modeller.dao.api.PrototypeDao;
import com.modeller.models.ContextObject;
import com.modeller.models.ExampleUserDetails;
import com.modeller.models.Page;
import com.modeller.models.Prototype;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import java.util.Calendar;

/**
 * Created by Gadzzzz on 24.02.2016.
 */
@Controller
public class Operations {

	@Autowired
	@Qualifier("prototypeDaoImpl")
	private PrototypeDao prototypeDao;

	@Autowired
	@Qualifier("pageDaoImpl")
	private PageDao pageDao;

	@RequestMapping(value = "/save",method = RequestMethod.POST)
	public void savePattern(@RequestBody ContextObject[][] contextObject, Authentication authentication){
		ExampleUserDetails user = (ExampleUserDetails) authentication.getPrincipal();
		Gson gson = new Gson();
		java.sql.Date timeNow = new java.sql.Date(Calendar.getInstance().getTimeInMillis());
		Prototype prototype = new Prototype(user.getId(),timeNow);
		int prototypeId = prototypeDao.save(prototype);
		for(int i=0;i<contextObject.length;i++){
			String json = gson.toJson(contextObject[i]);
			Page page = new Page(prototypeId,json);
			pageDao.save(page);
		}
	}
}
