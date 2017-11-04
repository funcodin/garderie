package com.garderie.types.user;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.garderie.types.base.BaseObjectMapperTest;
import com.garderie.types.user.info.Child;
import com.garderie.types.user.info.Parent;

public class ModelMapperTest extends BaseObjectMapperTest{

	private static final Logger log = LoggerFactory.getLogger(ModelMapperTest.class);
	
	@Test
	public void testParentReadAndWrite() throws Exception{
		Parent parent = this.baseInfoTest.createParent();
		log.info(this.objectMapper.writeValueAsString(parent));
		
	}
	
	@Test
	public void testChildReadAndWrite() throws Exception {
		final Child child = this.baseInfoTest.createChild();
		log.info(this.objectMapper.writeValueAsString(child));
	}
	
	
}
