package com.garderie.types.user.info;

import java.util.List;

import com.garderie.types.AbstractPersistable;

public class MedicalInformation extends AbstractPersistable{

	private List<Doctor> doctors;
	private List<String> medicalProblems; 
	private List<String> allergies;
	private List<String> medications;
	private List<String> foodAllergies;
	private BloodGroup bloodGroup;

	public List<Doctor> getDoctors() {
		return this.doctors;
	}
	public void setDoctors(final List<Doctor> doctors) {
		this.doctors = doctors;
	}
	public List<String> getMedicalProblems() {
		return this.medicalProblems;
	}
	public void setMedicalProblems(final List<String> medicalProblems) {
		this.medicalProblems = medicalProblems;
	}
	public List<String> getAllergies() {
		return this.allergies;
	}
	public void setAllergies(final List<String> allergies) {
		this.allergies = allergies;
	}
	public List<String> getMedications() {
		return this.medications;
	}
	public void setMedications(final List<String> medications) {
		this.medications = medications;
	}
	public List<String> getFoodAllergies() {
		return this.foodAllergies;
	}
	public void setFoodAllergies(final List<String> foodAllergies) {
		this.foodAllergies = foodAllergies;
	}
	public BloodGroup getBloodGroup() {
		return this.bloodGroup;
	}
	public void setBloodGroup(final BloodGroup bloodGroup) {
		this.bloodGroup = bloodGroup;
	}
	
}
