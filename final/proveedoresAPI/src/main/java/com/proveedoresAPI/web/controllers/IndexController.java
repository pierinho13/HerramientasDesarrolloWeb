package com.proveedoresAPI.web.controllers;

import java.util.Arrays;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.proveedoresAPI.web.infraestructura.comun.ApiUser;


@Controller
public class IndexController {
	
	
	@RequestMapping(value="/", method = RequestMethod.GET)
    public String getIndex(Model model, ApiUser user) {
		
		if (user==null) {
			user = new ApiUser("piero", "pass", Arrays.asList());
		}
		
		model.addAttribute("esAdmin", user.getIsAdmin());
		
        return "index";
    }
	
}