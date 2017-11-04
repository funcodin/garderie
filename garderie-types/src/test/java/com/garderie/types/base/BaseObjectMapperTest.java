package com.garderie.types.base;

import org.junit.Before;

import com.fasterxml.jackson.databind.ObjectMapper;

public abstract class BaseObjectMapperTest {

	
	public ObjectMapper objectMapper;
	public BaseInfoTest baseInfoTest;
	
	@Before
	public void setUp(){
		this.objectMapper = new ObjectMapper();
		this.baseInfoTest = new BaseInfoTest();
	}
}
