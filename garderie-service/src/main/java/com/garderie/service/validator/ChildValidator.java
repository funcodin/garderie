package com.garderie.service.validator;

import com.garderie.service.errors.GarderieErrors;
import com.garderie.types.user.info.Doctor;
import com.garderie.types.user.info.EmergencyContact;
import com.garderie.types.user.info.MedicalInformation;
import com.garderie.types.user.types.Child;
import com.garderie.types.user.types.Parent;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class ChildValidator extends AbstractValidator implements Validator<Child> {

    @Autowired
    private ParentValidator parentValidator;

    @Override
    public List<GarderieErrors> validatePreSave(final Child child) {

        final List<GarderieErrors> errors = new ArrayList<>();

        this.setDefaultValues(child);

        if(StringUtils.isBlank(child.getFirstName()))
            errors.add(GarderieErrors.MISSING_CHILD_FIRST_NAME);

        if(StringUtils.isBlank(child.getMiddleName()))
            errors.add(GarderieErrors.MISSING_CHILD_MIDDLE_NAME);

        if(StringUtils.isBlank(child.getLastName()))
            errors.add(GarderieErrors.MISSING_CHILD_LAST_NAME);




        if(Objects.isNull(child.getMedicalInformation())){
            errors.add(GarderieErrors.MISSING_MEDICAL_INFO);
        }else{

            final MedicalInformation medicalInformation = child.getMedicalInformation();
            this.setDefaultValues(medicalInformation);

            if(Objects.isNull(medicalInformation.getBloodGroup()))
                errors.add(GarderieErrors.MISSING_BLOOD_GROUP);

            if(CollectionUtils.isEmpty(medicalInformation.getDoctors())){
                errors.add(GarderieErrors.MISSING_DOCTOR_INFO);
            }else{
                for(final Doctor doctor : medicalInformation.getDoctors()) {

                    if(StringUtils.isBlank(doctor.getLastName()))
                        errors.add(GarderieErrors.MISSING_DOCTOR_LAST_NAME);

                    if(Objects.isNull(doctor.getContactDetails())){
                        errors.add(GarderieErrors.MISSING_DOCTOR_CONTACT_DETAILS);
                    }else{
                        if(CollectionUtils.isEmpty(doctor.getContactDetails().getCellPhoneNumbers()))
                            errors.add(GarderieErrors.MISSING_DOCTOR_CONTACT_CELLPHONE);
                    }
                }
            }
        }

        return errors;
    }


    @Override
    public List<GarderieErrors> validatePostSave(final Child child) {
        return null;
    }

    @Override
    public List<GarderieErrors> validate(Child child) {
        return null;
    }

}
