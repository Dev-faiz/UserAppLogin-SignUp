package com.app.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Fuck {
	
	@GetMapping("fuck")
	public String fuck() {return "Fuck" ; }
}
