package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.jdbc.autoconfigure.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class }) // これを書き足す
public class StudioNotesAppApplication {
	public static void main(String[] args) {
		SpringApplication.run(StudioNotesAppApplication.class, args);
	}
}