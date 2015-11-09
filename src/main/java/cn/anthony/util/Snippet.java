package cn.anthony.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class Snippet {

    public static void main(String[] args) {

	String password = "moscreen";
	PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	String hashedPassword = passwordEncoder.encode(password);
	System.out.println(hashedPassword);
    }
}
