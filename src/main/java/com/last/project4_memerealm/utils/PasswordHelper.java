package com.last.project4_memerealm.utils;

import at.favre.lib.crypto.bcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Value;

public class PasswordHelper {

	/**
	 * Hash password
	 * @return hashed password
	 */
	public static String hashPassword(String password){
		BCrypt.Hasher hasher = BCrypt.withDefaults();
		int cost = 10;
		return hasher.hashToString(cost, password.toCharArray());
	}

	/**
	 * verify plain password with hashed password
	 * @return boolean
	 */
	public static boolean verifyPassword(String password, String hashedPassword){
		BCrypt.Verifyer verifyer = BCrypt.verifyer();
		return verifyer.verify(password.toCharArray(), hashedPassword).verified;
	}
}
