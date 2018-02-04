package com.garderie.service.validator;

import com.garderie.service.errors.GarderieErrors;
import com.garderie.types.AbstractPersistable;
import com.garderie.types.user.info.Address;
import com.garderie.types.user.info.ContactDetails;
import com.garderie.types.user.info.User;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class UserValidator extends AbstractValidator implements Validator<User> {

    @Override
    public List<GarderieErrors> validatePreSave( final User user) {
        final List<GarderieErrors> errors = new ArrayList<>();

        if(StringUtils.isBlank(user.getEmailId()))
            errors.add(GarderieErrors.MISSING_EMAIL);

        if(StringUtils.isBlank(user.getFirstName()))
            errors.add(GarderieErrors.MISSING_FIRST_NAME);

        if(StringUtils.isBlank(user.getLastName()))
            errors.add(GarderieErrors.MISSING_LAST_NAME);

        if(Objects.isNull(user.getAddress())) {
            errors.add(GarderieErrors.MISSING_ADDRESS);
        }else{
            this.validateAddress(user.getAddress(),errors);
        }

        if(Objects.isNull(user.getContactDetails())) {
            errors.add(GarderieErrors.MISSING_CONTACT_DETAILS);
        }else{
            final ContactDetails contactDetails = user.getContactDetails();
            this.setDefaultValues(contactDetails);

            if(CollectionUtils.isEmpty(contactDetails.getCellPhoneNumbers())){
               errors.add(GarderieErrors.MISSING_CELL_PHONE_NUMBER);
            }
        }

        return errors;
    }

    @Override
    public List<GarderieErrors> validatePostSave( final User user) {
        final List<GarderieErrors> errors = new ArrayList<>();
        return errors;
    }

    @Override
    public List<GarderieErrors> validate(User user) {
        return null;
    }

    public void validateAddress(final Address address, final List<GarderieErrors> errors){

        this.setDefaultValues(address);

        if(StringUtils.isBlank(address.getStreetAddress()))
            errors.add(GarderieErrors.MISSING_STREET_ADDRESS);

        if(StringUtils.isBlank(address.getCity()))
            errors.add(GarderieErrors.MISSING_CITY);

        if(StringUtils.isBlank(address.getState()))
            errors.add(GarderieErrors.MISSING_STATE);

        if(StringUtils.isBlank(address.getZipCode()))
            errors.add(GarderieErrors.MISSING_ZIP_CODE);

        if(StringUtils.isBlank(address.getCountry()))
            errors.add(GarderieErrors.MISSING_COUNTRY);
    }
}
