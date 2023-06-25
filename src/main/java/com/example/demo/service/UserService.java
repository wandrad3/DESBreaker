package com.example.demo.service;

import com.example.demo.domain.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.secure.encrypt.EncryptToDES;

public class UserService {

	UserRepository repository = new UserRepository();

	public boolean validateUser(User userPost) {
		User userReal = repository.findUser(userPost);	
		if (EncryptToDES.encriptGeneratedWordToDES(userPost.getPassword()).equals(userReal.getPassword())) {
			return true;
		}

		return false;
	}

	public String redefinePassword(User userJoao) {
		String success = repository.save(userJoao);
		
		return success;
	}

}
