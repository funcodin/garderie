package com.garderie.types.base;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.garderie.types.user.info.Address;
import com.garderie.types.user.info.BloodGroup;
import com.garderie.types.user.info.Child;
import com.garderie.types.user.info.ContactDetails;
import com.garderie.types.user.info.Doctor;
import com.garderie.types.user.info.DoctorType;
import com.garderie.types.user.info.EmergencyContact;
import com.garderie.types.user.info.Gender;
import com.garderie.types.user.info.MedicalInformation;
import com.garderie.types.user.info.Parent;
import com.garderie.types.user.info.Relation;
import com.garderie.types.user.info.User;
import com.garderie.types.user.info.UserType;

public class BaseInfoTest {

	
	
	public Address createAddress(){
		Address address = new Address();
		address.setId(UUID.randomUUID().toString());
		address.setCreatedDate(new Date());
		address.setModifiedDate(new Date());
		address.setStreetAddress("3146 Oak Raod Apt 212");
		address.setCity("Walnut Creek");
		address.setState("CA");
		address.setCountry("USA");
		address.setZipCode("91497");
		return address;
	}
	
	public ContactDetails createContactDetails(){
		final ContactDetails contactDetails = new ContactDetails();
		contactDetails.setId(UUID.randomUUID().toString());
		contactDetails.setCreatedDate(new Date());
		contactDetails.setModifiedDate(new Date());
		List<String> cellPhoneNumbers = new ArrayList<>();
		cellPhoneNumbers.add("201-565-6543");
		cellPhoneNumbers.add("123-456-7654");
		contactDetails.setCellPhoneNumbers(cellPhoneNumbers);
		contactDetails.setHomePhoneNumber("232300");
		return contactDetails;
	}
	
	
	public Parent createParent(){
		final Parent parent = new Parent();
		parent.setFirstName("Rohit");
		parent.setMiddleName("Vijay");
		parent.setLastName("Dhumal");
		parent.setBirthDate(new Date());
		parent.setEmailId("rohitdhumal09@gmail.com");
		parent.setGender(Gender.MALE);
		parent.setAddress(this.createAddress());
		parent.setContactDetails(this.createContactDetails());
		parent.setId(UUID.randomUUID().toString());
		parent.setCreatedDate(new Date());
		parent.setModifiedDate(new Date());
		parent.setUserType(UserType.PARENT);
		parent.setRelationshipToChild(Relation.FATHER);
		return parent;
	}
	
	public EmergencyContact createEmergencyContact(){
		final EmergencyContact emergencyContact = new EmergencyContact();
		emergencyContact.setId(UUID.randomUUID().toString());
		emergencyContact.setCreatedDate(new Date());
		emergencyContact.setModifiedDate(new Date());
		emergencyContact.setCellPhone("201-565-6543");
		emergencyContact.setFirstName("Emergency fname");
		emergencyContact.setMiddleName("emer middle");
		emergencyContact.setLastName("emer last");
		emergencyContact.setRelationshipToChild(Relation.BROTHER);
		return emergencyContact;
	}
	
	public Doctor createDoctor(){
		final Doctor doctor = new Doctor();
		doctor.setId(UUID.randomUUID().toString());
		doctor.setCreatedDate(new Date());
		doctor.setModifiedDate(new Date());
		doctor.setAddress(this.createAddress());
		doctor.setContactDetails(this.createContactDetails());
		doctor.setDoctorType(DoctorType.GENERAL);
		doctor.setFirstName("Ashok");
		doctor.setMiddleName("C");
		doctor.setLastName("Sukumar");
		return doctor;
		
	}
	
	public MedicalInformation createMedicalInformation(){
		final MedicalInformation medicalInformation = new MedicalInformation();
		medicalInformation.setId(UUID.randomUUID().toString());
		medicalInformation.setCreatedDate(new Date());
		medicalInformation.setModifiedDate(new Date());
		final List<String> allergies = new ArrayList<>();
		allergies.add("peanut");
		allergies.add("peach");
		medicalInformation.setAllergies(allergies);
		
		final List<String> foodAllergies = new ArrayList<>();
		foodAllergies.add("pizza");
		foodAllergies.add("tacos");
		medicalInformation.setFoodAllergies(foodAllergies);
		
		final List<Doctor> doctors = new ArrayList<>();
		doctors.add(this.createDoctor());
		medicalInformation.setDoctors(doctors);
		medicalInformation.setBloodGroup(BloodGroup.O_POSITIVE);
		
		return medicalInformation;
				
	}
	
	public Child createChild(){
		final Child child = new Child();
		child.setId(UUID.randomUUID().toString());
		child.setCreatedDate(new Date());
		child.setModifiedDate(new Date());
		child.setFirstName("Kid");
		child.setMiddleName("A");
		child.setLastName("R");
		child.setOrgId("1233");
		
		final List<EmergencyContact> emergencyContacts = new ArrayList<>();
		emergencyContacts.add(this.createEmergencyContact());
		child.setEmergencyContacts(emergencyContacts);
		final List<Parent> parents = new ArrayList<>();
		parents.add(this.createParent());
		child.setParents(parents);
		child.setMedicalInformation(this.createMedicalInformation());
		return child;
		
	}
}
