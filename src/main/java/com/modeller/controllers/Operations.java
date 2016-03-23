package com.modeller.controllers;

import com.google.gson.Gson;
import com.modeller.dao.api.PageDao;
import com.modeller.dao.api.PrototypeDao;
import com.modeller.dao.api.ShareDao;
import com.modeller.models.*;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
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

	@Autowired
	@Qualifier("shareDaoImpl")
	private ShareDao shareDao;

	@RequestMapping(value = "/save",method = RequestMethod.POST)
	public void savePrototype(@RequestBody JSONObject jsonObject,
									  Authentication authentication){
		ExampleUserDetails user = (ExampleUserDetails) authentication.getPrincipal();
		Gson gson = new Gson();
		java.sql.Date timeNow = new java.sql.Date(Calendar.getInstance().getTimeInMillis());
		String link = RandomStringUtils.randomAlphanumeric(40).toUpperCase();
		Prototype prototype = new Prototype(user.getId(),timeNow,jsonObject.getName(),link);
		if(!prototypeDao.exist(jsonObject.getName(),user.getId())){
			int prototypeId = prototypeDao.save(prototype);
			for(int i=0;i<jsonObject.getContextObjects().length;i++){
				String json = gson.toJson(jsonObject.getContextObjects()[i]);
				Page page = new Page(prototypeId,json);
				pageDao.save(page);
			}
		}else {
			int prototypeId = prototypeDao.lodaByName(prototype.getName());
			prototype.setId(prototypeId);
			prototypeDao.update(prototype);
			pageDao.delete(prototypeId);
			for (int i = 0; i < jsonObject.getContextObjects().length; i++) {
				String json = gson.toJson(jsonObject.getContextObjects()[i]);
				Page page = new Page(prototypeId, json);
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

	@RequestMapping(value = "/loadByLink",method = RequestMethod.POST)
	public @ResponseBody Prototype loadByLink(HttpServletRequest request){
		String link = request.getParameter("link");
		Prototype prototype = prototypeDao.loadByLink(link);
		List<Page> pages = pageDao.load(prototype.getId());
		prototype.setPageList(pages);
		return prototype;
	}

	@RequestMapping(value = "/loadall",method = RequestMethod.POST)
	public @ResponseBody List<Prototype> loadPrototypes(Authentication authentication){
		ExampleUserDetails user = (ExampleUserDetails) authentication.getPrincipal();
		List<Prototype> prototypes = prototypeDao.loadAll(user.getId());
		return prototypes;
	}

	@RequestMapping(value = "/delete",method = RequestMethod.POST)
	public @ResponseBody String deletePrototype(HttpServletRequest request){
		int prototypeId = Integer.valueOf(request.getParameter("prototypeId"));
		Prototype prototype = prototypeDao.load(prototypeId);
		shareDao.delete(prototype.getLink());
		pageDao.delete(prototypeId);
		prototypeDao.delete(prototypeId);
		return "ok";
	}

	@RequestMapping(value = "/deleteShare",method = RequestMethod.POST)
	public @ResponseBody String deleteShare(HttpServletRequest request){
		int shareId = Integer.valueOf(request.getParameter("shareId"));
		shareDao.delete(shareId);
		return "ok";
	}

	@RequestMapping(value = "/updateName",method = RequestMethod.POST)
	public @ResponseBody String updateName(HttpServletRequest request){
		int prototypeId = Integer.valueOf(request.getParameter("prototypeId"));
		String name = request.getParameter("prototypeName");
		Prototype prototype = new Prototype();
		prototype.setId(prototypeId);
		prototype.setName(name);
		prototypeDao.updateName(prototype);
		return "ok";
	}

}
