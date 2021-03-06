package com.garderie.types.base;

import java.util.*;

import com.garderie.types.activites.Activity;
import com.garderie.types.activites.ActivityType;
import com.garderie.types.org.*;
import com.garderie.types.user.info.*;
import com.garderie.types.user.types.Child;
import com.garderie.types.user.types.Parent;
import com.garderie.types.user.types.Teacher;

public class BaseInfoTest {

    public Address createAddress() {
        Address address = new Address();
        address.setStreetAddress("3146 Oak Raod Apt 212");
        address.setCity("Walnut Creek");
        address.setState("CA");
        address.setCountry("USA");
        address.setZipCode("91497");
        return address;
    }

    public ContactDetails createContactDetails() {
        final ContactDetails contactDetails = new ContactDetails();
        Set<String> cellPhoneNumbers = new HashSet<>();
        cellPhoneNumbers.add("201-565-6543");
        cellPhoneNumbers.add("123-456-7654");
        contactDetails.setCellPhoneNumbers(cellPhoneNumbers);
        contactDetails.setHomePhoneNumber("232300");
        return contactDetails;
    }

    public OrgOwner createOrgOwner() {
        final OrgOwner orgOwner = new OrgOwner();
        orgOwner.setContactDetails(this.createContactDetails());
        orgOwner.setFirstName("Rohit");
        orgOwner.setMiddleName("Vijay");
        orgOwner.setLastName("Dhumal");
        return orgOwner;
    }

    public Teacher createTeacher() {
        final Teacher teacher = new Teacher();
        teacher.setFirstName("Teacher");
        teacher.setLastName("Teacher");
        teacher.setGender(Gender.FEMALE);
        teacher.setAddress(this.createAddress());
        teacher.setContactDetails(this.createContactDetails());
        teacher.setId(UUID.randomUUID().toString());
        return teacher;
    }

    public Parent createParent() {
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

    public EmergencyContact createEmergencyContact() {
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

    public Doctor createDoctor() {
        final Doctor doctor = new Doctor();
        doctor.setContactDetails(this.createContactDetails());
        doctor.setDoctorType(DoctorType.GENERAL);
        doctor.setFirstName("Ashok");
        doctor.setMiddleName("C");
        doctor.setLastName("Sukumar");
        return doctor;

    }

    public MedicalInformation createMedicalInformation() {
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

        final List<String> medicalProblems = new ArrayList<>();
        medicalProblems.add("Sleepy head");
        medicalProblems.add("Bhasmya");
        medicalInformation.setMedicalProblems(medicalProblems);


        final List<Doctor> doctors = new ArrayList<>();
        doctors.add(this.createDoctor());
        medicalInformation.setDoctors(doctors);
        medicalInformation.setBloodGroup(BloodGroup.O_POSITIVE);

        return medicalInformation;

    }

    public Child createChild() {
        final Child child = new Child();
        child.setFirstName("Kid");
        child.setLastName("R");
        child.setOrgId("1233");

        child.setMedicalInformation(this.createMedicalInformation());
        return child;

    }

    public Organisation createOrganization() {
        final Organisation organization = new Organisation();
        organization.setOrganisationAddress(this.createAddress());
        organization.setOrgOwnerId(UUID.randomUUID().toString());
        organization.setOrgName("Garderie Institute");
        //organization.setOrgOwner(this.createOrgOwner());
        organization.setId(UUID.randomUUID().toString());
        organization.setCreatedDate(new Date());
        organization.setModifiedDate(new Date());
        return organization;
    }

    public Classroom createClassroom() {
        final Classroom classroom = new Classroom();
        classroom.setName("Class room 1");
        classroom.setOrgId(UUID.randomUUID().toString());
        classroom.setId(UUID.randomUUID().toString());
        classroom.setCreatedDate(new Date());
        classroom.setModifiedDate(new Date());
        Set<String> studentIds = new HashSet<>();
        studentIds.add(UUID.randomUUID().toString());
        studentIds.add(UUID.randomUUID().toString());
        classroom.setStudentIds(studentIds);
        return classroom;
    }

    public Activity createActivity() {
        final Activity activity = new Activity();
        activity.setActivityCreatedById(UUID.randomUUID().toString());
        activity.setActivityType(ActivityType.AFTERNOON_NAP);
        activity.setCaption("Happy napping");
        activity.setChildId(UUID.randomUUID().toString());
        activity.setClassId(UUID.randomUUID().toString());
        activity.setPublic(false);
        activity.setCreatedDate(new Date());
        activity.setModifiedDate(new Date());
        activity.setId(UUID.randomUUID().toString());
        return activity;
    }

}
