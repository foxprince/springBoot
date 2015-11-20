package cn.anthony.boot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;

public class TestService implements CommandLineRunner {

    @Autowired
    PhoneHeadService phService;

    @Override
    public void run(String... arg0) throws Exception {
	System.out.println(phService.getAllProvince());
    }

    public static void main(String[] args) {
	SpringApplication.run(TestService.class, args);

    }

}
