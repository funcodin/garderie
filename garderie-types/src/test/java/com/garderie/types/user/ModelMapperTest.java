package com.garderie.types.user;

import com.garderie.types.activites.Activity;
import com.garderie.types.org.Classroom;
import com.garderie.types.org.Organization;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.garderie.types.base.BaseObjectMapperTest;
import com.garderie.types.user.types.Child;
import com.garderie.types.user.types.Parent;

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

	@Test
	public void testOrganisationReadAndWrtie() throws Exception {
		final Organization organization = this.baseInfoTest.createOrganization();
		log.info(this.objectMapper.writeValueAsString(organization));
	}

	@Test
	public void testClassroomReadAndWrite() throws Exception {
		final Classroom classroom = this.baseInfoTest.createClassroom();
		log.info(this.objectMapper.writeValueAsString(classroom));
	}

	@Test
	public void testActivityReadAndWrite() throws Exception {
		final Activity activity = this.baseInfoTest.createActivity();
		log.info(this.objectMapper.writeValueAsString(activity));
	}
	
}
