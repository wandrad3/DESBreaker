package com.br.wes.thread;

import java.util.concurrent.Callable;

import com.br.wes.tool.PasswordBreakerContextA;

public class BreakPasswordProcessorContextA implements Callable<Boolean> {


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

	

	public BreakPasswordProcessorContextA(String user, int i) {
		this.user = user;
		this.i = i;
	}

	@Override
	public Boolean call() throws Exception {
		PasswordBreakerContextA breaker = new PasswordBreakerContextA();
		return breaker.breakPassword(this.getUser(), this.getI());
	
	}

	

	
	
}
