package com.garderie.types.user.types;

import com.garderie.types.AbstractPersistable;
import com.garderie.types.user.info.Address;
import com.garderie.types.user.info.MedicalInformation;

import java.util.Set;

public class Child extends AbstractPersistable {

    private String firstName;
    private String middleName;
    private String lastName;
    private Set<String> parentIds;
    private String orgId;
    private Set<String> classroomIds;
    private Address address;
    private MedicalInformation medicalInformation;

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(final String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return this.middleName;
    }

    public void setMiddleName(final String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(final String lastName) {
        this.lastName = lastName;
    }

    public Set<String> getParentIds() {
        return this.parentIds;
    }

    public void setParentIds(final Set<String> parentIds) {
        this.parentIds = parentIds;
    }

    public String getOrgId() {
        return this.orgId;
    }

    public void setOrgId(final String orgId) {
        this.orgId = orgId;
    }

    public Set<String> getClassroomIds() {
        return this.classroomIds;
    }

    public void setClassroomIds(final Set<String> classroomIds) {
        this.classroomIds = classroomIds;
    }

    public MedicalInformation getMedicalInformation() {
        return this.medicalInformation;
    }

    public void setMedicalInformation(final MedicalInformation medicalInformation) {
        this.medicalInformation = medicalInformation;
    }

    public Address getAddress() {
        return this.address;
    }

    public void setAddress(final Address address) {
        this.address = address;
    }
}
