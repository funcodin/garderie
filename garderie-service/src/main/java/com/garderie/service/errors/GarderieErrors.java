package com.garderie.service.errors;

public enum GarderieErrors {

    MISSING_EMAIL,
    MISSING_BIRTH_DATE,
    MISSING_FIRST_NAME,
    MISSING_LAST_NAME,
    MISSING_ADDRESS,
    MISSING_STREET_ADDRESS,
    MISSING_CITY,
    MISSING_STATE,
    MISSING_ZIP_CODE,
    MISSING_COUNTRY,
    MISSING_CONTACT_DETAILS,
    MISSING_CELL_PHONE_NUMBER,
    MISSING_RELATIONSHIP_TO_CHILD,
    MISSING_CHILDREN_IDS,

    //CHILD ERRORS
    MISSING_CHILD_FIRST_NAME,
    MISSING_CHILD_MIDDLE_NAME,
    MISSING_CHILD_LAST_NAME,

    //EMERGENCY CONTACT
    MISSING_EMERGENCY_CONTACT,
    MISSING_EMERGENCY_FIRST_NAME,
    MISSING_EMERGENCY_LAST_NAME,
    MISSING_EMERGENCY_CELLPHONE,
    MISSING_EMERGENCY_RELATIONSHIP,
    MISSING_PARENTS_INFO,

    //MEDICAL INFO
    MISSING_MEDICAL_INFO,
    MISSING_BLOOD_GROUP,

    //DOCTOR INFO
    MISSING_DOCTOR_INFO,
    MISSING_DOCTOR_LAST_NAME,
    MISSING_DOCTOR_CONTACT_DETAILS,
    MISSING_DOCTOR_CONTACT_CELLPHONE,
    MISSING_DOCTOR_TYPE,

    //SALT INFO
    MISSING_USER_SALT,

    //LOGIN INFO
    MISSING_PASSWORD,
    BAD_SIGNUP_REQUEST,
    MISSING_SECRET_CODE,
    PASSWORD_MISMATCH,

    //ActionPermissions
    ACTION_PERMISSIONS,

    //ORG SIGNUP ERROR;
    MISSING_ORGANISATION,
    ORG_NAME_MISSING,
    ORG_OWNER_FNAME_MISSING,
    ORG_OWNER_LNAME_MISSING,
    MISSING_ORG_STREET_NAME,
    MISSING_ORG_CITY,
    MISSING_ORG_STATE,
    MISSING_ORG_ZIPCODE,
    MISSING_ORG_COUNTRY;
}
