package com.healthlife.u20221b471.main.medicalhistoryservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

import java.sql.*;
@EnableFeignClients(basePackages = "com.healthlife.u20221b471.main.medicalhistoryservice")

@SpringBootApplication
public class MedicalHistoryServiceApplication {

	public static void main(String[] args) {
		createDatabaseIfNotExists("jdbc:postgresql://localhost:5432/", "postgres", "12345678", "nutrimove_medicalhistory");

		SpringApplication.run(MedicalHistoryServiceApplication.class, args);
	}
	private static void createDatabaseIfNotExists(String url, String user, String password, String dbName) {
		try (Connection conn = DriverManager.getConnection(url, user, password);
			 Statement stmt = conn.createStatement()) {

			ResultSet rs = stmt.executeQuery("SELECT 1 FROM pg_database WHERE datname = '" + dbName + "'");
			if (!rs.next()) {
				stmt.executeUpdate("CREATE DATABASE " + dbName);
				System.out.println("Base de datos '" + dbName + "' creada correctamente.");
			}

		} catch (SQLException e) {
			throw new RuntimeException("Error verificando/creando base de datos: " + dbName, e);
		}
	}
}
