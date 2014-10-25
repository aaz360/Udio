package eu.udio.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class SiteController {	
	
	@RequestMapping(value="/",method = RequestMethod.GET)
	public String showHome(){		
		return "/home";
	}
	
	@RequestMapping(value="/main",method = RequestMethod.GET)
	public String login(){		
		return "/main";
	}

	@RequestMapping(value="/upload.html",method = RequestMethod.GET)
	public String upload(){		
		return "/upload";
	}
		
}
