package com.modeller.controllers;

import com.modeller.dao.api.PrototypeDao;
import com.modeller.dao.api.ShareDao;
import com.modeller.models.ExampleUserDetails;
import com.modeller.models.Prototype;
import com.modeller.models.Share;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gadzzzz on 22.03.2016.
 */
@Controller
public class Cabinet {

	@Autowired
	@Qualifier("prototypeDaoImpl")
	private PrototypeDao prototypeDao;

	@Autowired
	@Qualifier("shareDaoImpl")
	private ShareDao shareDao;

	@RequestMapping(value = "/cabinet",method = RequestMethod.GET)
	public String loadPrototypes(HttpServletRequest request, Authentication authentication){
		ExampleUserDetails user = (ExampleUserDetails) authentication.getPrincipal();
		List<Prototype> prototypes = prototypeDao.loadAll(user.getId());
		List<Share> shares = shareDao.loadAll(user.getId());
		List<Prototype> sharePrototypes = new ArrayList<Prototype>();
		for(int i=0;i<shares.size();i++){
			sharePrototypes.add(prototypeDao.loadByLink(shares.get(i).getLink()));
		}
		request.setAttribute("myid",user.getId());
		request.setAttribute("protorypes",prototypes);
		request.setAttribute("shares",sharePrototypes);
		return "cabinet";
	}

	@RequestMapping(value = "/share", method = RequestMethod.POST)
	public @ResponseBody String share(HttpServletRequest request){
		String link = request.getParameter("link");
		int to = Integer.valueOf(request.getParameter("to"));
		if(!shareDao.exist(to,link)) {
			Share share = new Share(to, link);
			shareDao.save(share);
		}
		return "ok";
	}
}
