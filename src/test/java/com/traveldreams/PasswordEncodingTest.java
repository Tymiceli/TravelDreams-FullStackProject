package com.traveldreams;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class PasswordEncodingTest {
	@Test
	public void encrypt_password () {
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		
		String encodedPassword = passwordEncoder.encode("Password123");
		
		System.out.println(encodedPassword);
		
//		System.out.println(new BCryptPasswordEncoder().encode("password123"));
	}

}
