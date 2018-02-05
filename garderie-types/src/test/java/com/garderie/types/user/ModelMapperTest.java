package com.garderie.types.user;

import com.garderie.types.activites.Activity;
import com.garderie.types.base.BaseObjectMapperTest;
import com.garderie.types.dto.SignUpDTO;
import com.garderie.types.org.Classroom;
import com.garderie.types.org.OrgOwner;
import com.garderie.types.org.Organisation;
import com.garderie.types.user.types.Child;
import com.garderie.types.user.types.Parent;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
		final Organisation organization = this.baseInfoTest.createOrganization();
		log.info(this.objectMapper.writeValueAsString(organization));
	}

	@Test
	public void testOrganisationOwnerReadAndWrite() throws Exception {
		final OrgOwner orgOwner = this.baseInfoTest.createOrgOwner();
		log.info(this.objectMapper.writeValueAsString(orgOwner));
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

	@Test
	public void testSignupDTO() throws Exception {
		final SignUpDTO signUpDTO = new SignUpDTO();
		signUpDTO.setEmailId("rvd@gmail.com");
		signUpDTO.setPassword("password");
		signUpDTO.setConfirmPassword("password");
		log.info(this.objectMapper.writeValueAsString(signUpDTO));

	}
	
}
