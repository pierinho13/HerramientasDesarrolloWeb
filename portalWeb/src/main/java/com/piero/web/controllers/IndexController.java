package com.piero.web.controllers;

import java.util.Arrays;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.piero.web.infraestructura.comun.AppUser;


@Controller
public class IndexController {
	
	
	@RequestMapping(value="/", method = RequestMethod.GET)
    public String getIndex(Model model, AppUser user) {
		
		if (user==null) {
			user = new AppUser("piero", "pass", Arrays.asList());
		}
		
		model.addAttribute("palabra", "prueba");
		
        return "index";
    }
	
}