package com.br.wes.thread;

import java.util.concurrent.Callable;

import com.br.wes.tool.PasswordBreakerContextC;

public class BreakPasswordProcessorContextC implements Callable<Boolean> {
	Integer i;
	String user;

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}
	public Integer getI() {
		return i;
	}

	public void setI(Integer i) {
		this.i = i;
	}


	public BreakPasswordProcessorContextC(String user, int i) {
		this.i = i;
	}

	@Override
	public Boolean call() throws Exception {
		PasswordBreakerContextC breaker = new PasswordBreakerContextC();
		//return breaker.breakPassword(breaker.encriptGeneratedWordToDES(this.getDesPassword()),this.getI());
		return breaker.breakPassword(this.getUser(), this.getI());
	}

	

	
	
}
