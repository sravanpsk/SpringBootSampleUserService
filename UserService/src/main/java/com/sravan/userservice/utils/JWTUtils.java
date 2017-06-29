package com.sravan.userservice.utils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;

import org.apache.axiom.util.base64.Base64Utils;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JWTUtils {
	private static final String SUBSCRIBER = "http://wso2.org/claims/subscriber";
	private static final String USER_NAME = "http://wso2.org/claims/enduser";

	/**
	 * Bas64 Encoder and Decoder
	 * 
	 * @param strEncoded
	 * @return
	 * @throws IOException
	 */
	public static Map<String, Object> decode(String strEncoded) throws IOException {
		ObjectMapper mapper = new ObjectMapper();

		String signedJWTToken = strEncoded;
		final int num = 2;
		String[] splitString = signedJWTToken.split("\\.");
		String base64EncodedHeader = splitString[0];
		String base64EncodedBody = splitString[1];
		String base64EncodedCrypto = splitString[num];

		new String(Base64Utils.decode(base64EncodedHeader), StandardCharsets.UTF_8);
		String decodedBody = new String(Base64Utils.decode(base64EncodedBody), StandardCharsets.UTF_8);
		new String(Base64Utils.decode(base64EncodedCrypto), StandardCharsets.UTF_8);
		decodedBody = decodedBody.replace("\\", "");

		return mapper.readValue(decodedBody, Map.class);
	}

	/**
	 * Read the user information and return it.
	 * 
	 * @param grants
	 * @return String user
	 */
	public static String getUser(Map<String, Object> grants) {
		if (grants != null) {
			return grants.get(USER_NAME).toString();
		}

		return "unknown-user";

	}

	/**
	 * Read the subscriber and return it.
	 * 
	 * @param grants
	 * @return String subscriber
	 */
	public static String getSubscriber(Map<String, Object> grants) {
		if (grants != null) {
			return grants.get(SUBSCRIBER).toString();
		}

		return "unknown-subscriber";
	}
}
