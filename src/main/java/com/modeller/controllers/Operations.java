package com.modeller.controllers;

import com.google.gson.Gson;
import com.modeller.dao.api.PageDao;
import com.modeller.dao.api.PrototypeDao;
import com.modeller.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Calendar;
import java.util.List;

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
	public void savePrototype(@RequestBody JSONObject jsonObject,
									  Authentication authentication){
		ExampleUserDetails user = (ExampleUserDetails) authentication.getPrincipal();
		Gson gson = new Gson();
		java.sql.Date timeNow = new java.sql.Date(Calendar.getInstance().getTimeInMillis());
		Prototype prototype = new Prototype(user.getId(),timeNow,jsonObject.getName());
		if(jsonObject.getId()==0){
			int prototypeId = prototypeDao.save(prototype);
			for(int i=0;i<jsonObject.getContextObjects().length;i++){
				String json = gson.toJson(jsonObject.getContextObjects()[i]);
				Page page = new Page(prototypeId,json);
				pageDao.save(page);
			}
		}else {
			prototypeDao.update(prototype);
			pageDao.delete(jsonObject.getId());
			for (int i = 0; i < jsonObject.getContextObjects().length; i++) {
				String json = gson.toJson(jsonObject.getContextObjects()[i]);
				Page page = new Page(jsonObject.getId(), json);
				pageDao.save(page);
			}
		}
	}

	@RequestMapping(value = "/load",method = RequestMethod.POST)
	public @ResponseBody Prototype loadPrototype(@RequestBody int prototypeId){
		Prototype prototype = prototypeDao.load(prototypeId);
		List<Page> pages = pageDao.load(prototypeId);
		prototype.setPageList(pages);
		return prototype;
	}

	@RequestMapping(value = "/loadall",method = RequestMethod.POST)
	public @ResponseBody List<Prototype> loadPrototypes(Authentication authentication){
		ExampleUserDetails user = (ExampleUserDetails) authentication.getPrincipal();
		List<Prototype> prototypes = prototypeDao.loadAll(user.getId());
		return prototypes;
	}
}
