package com.example.demo.repository;

import com.example.demo.domain.User;
import com.example.demo.secure.encrypt.EncryptToDES;

public class UserRepository {


	User userA = new User();
	
	public User findUser(User userB) {
		if(EncryptToDES.encriptGeneratedWordToDES(userB.getPassword()).equals(userA.getPassword())) {
			return userA;
		}
		
		return userB;
		
	}

	public String save(User user) {
		userA.setLogin(user.getLogin());
		userA.setPassword(EncryptToDES.encriptGeneratedWordToDES(user.getPassword()));
		return userA.toString() + " | "+ "senha alterada com sucesso!";
	}

}
