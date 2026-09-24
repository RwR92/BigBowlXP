package com.example.BigBowlProjekt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.sql.DataSource;
import java.sql.SQLException;

@SpringBootApplication
public class BigBowlProjektApplication {

	public static void main(String[] args) {
		var context = SpringApplication.run(BigBowlProjektApplication.class, args);

	// GET db url
		String dbURL;
		var datasource = context.getBean(DataSource.class);
		try{
			dbURL = datasource.getConnection().getMetaData().getURL();
		} catch (SQLException e){
			throw new RuntimeException(e);
		}

		String[] profiles = context.getEnvironment().getActiveProfiles();
		String activeProfiles = profiles.length == 0 ? "default" : String.join(", ",profiles);

		System.out.println("------------");
		System.out.println("DB URL: " + dbURL);
		System.out.println("Active profiles: " + activeProfiles);
		System.out.println("------------");

	}

}
