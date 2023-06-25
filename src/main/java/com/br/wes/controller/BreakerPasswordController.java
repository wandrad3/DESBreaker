package com.br.wes.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.br.wes.thread.BreakPasswordExecutor;

@RestController
@RequestMapping("/v1")
public class BreakerPasswordController {
	
	BreakPasswordExecutor breakPasswordExecutor = new BreakPasswordExecutor();
	
	@GetMapping("/breakpassword/")
	public ResponseEntity<String> validUSer(@RequestParam("user") String user,@RequestParam("length") Integer length) {

		return breakPasswordExecutor.passwordWasBreak(user,length) ? ResponseEntity.ok("Senha quebrada. Log salvo em /DESBreaker/src/main/resources/result")
				: ResponseEntity.ok("");
	}

}
