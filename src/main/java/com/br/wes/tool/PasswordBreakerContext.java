package com.br.wes.tool;

public interface PasswordBreakerContext {

	public boolean breakPassword(String user,int length);
	public String encriptGeneratedWordToDES(String generatedWord);
	
}
