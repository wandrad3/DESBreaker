package com.br.wes.thread;

import java.util.concurrent.Callable;

import com.br.wes.tool.PasswordBreakerContextB;

public class BreakPasswordProcessorContextB implements Callable<Boolean> {

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

	public BreakPasswordProcessorContextB(String user,int i) {
		this.user = user;
		this.i = i;
	}

	@Override
	public Boolean call() throws Exception {
		PasswordBreakerContextB breaker = new PasswordBreakerContextB();
		// return
		// breaker.breakPassword(breaker.encriptGeneratedWordToDES(this.getDesPassword()),this.getI());
		return breaker.breakPassword(this.getUser(), this.getI());

	}

}
