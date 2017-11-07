//package com.hotwheels.repository.config;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
//import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
//import org.springframework.web.servlet.config.annotation.EnableWebMvc;
//
//import com.mongodb.MongoClient;
//
//@Configuration
//@EnableWebMvc
//@EnableMongoRepositories(basePackages = "com.hotwheels.repository")
//public class MondoDBConfig extends AbstractMongoConfiguration{
//
//	@Override
//	public MongoClient mongoClient() {
//		return new MongoClient("127.0.0.1",27017);
//	}
//
//	@Override
//	protected String getDatabaseName() {
//		return "test";
//	}
//
//	@Override
//    protected String getMappingBasePackage() {
//        return "com.hotwheels";
//    }
//	
//}
