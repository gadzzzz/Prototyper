package com.modeller.controllers;

import com.google.gson.Gson;
import com.modeller.dao.api.PrototypeDao;
import com.modeller.models.ContextObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Gadzzzz on 24.02.2016.
 */
@Controller
public class Operations {

	@Autowired
	@Qualifier("prototypeDaoImpl")
	private PrototypeDao prototypeDao;

	@RequestMapping(value = "/save",method = RequestMethod.POST)
	public void savePattern(@RequestBody ContextObject[] contextObject,HttpServletRequest req){
		Gson gson = new Gson();
		String json = gson.toJson(contextObject);
		System.out.println(json);
	}
}
