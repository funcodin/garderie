package com.garderie.service.validator;

import com.garderie.service.errors.GarderieErrors;
import com.garderie.types.user.info.UserType;
import com.garderie.types.user.types.Parent;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class ParentValidator extends AbstractValidator implements Validator<Parent> {

    @Autowired
    private UserValidator userValidator;

    private static final Logger log = LoggerFactory.getLogger(ParentValidator.class);
    @Override
    public List<GarderieErrors> validatePreSave(final Parent parent) {

        final List<GarderieErrors> errors = new ArrayList<>();

        this.setDefaultValues(parent);

        if(Objects.isNull(parent.getUserType()))
            parent.setUserType(UserType.PARENT);

        if(Objects.isNull(parent.getRelationshipToChild()))
            errors.add(GarderieErrors.MISSING_RELATIONSHIP_TO_CHILD);

        errors.addAll(this.userValidator.validatePreSave(parent));
        return errors;
    }

    @Override
    public List<GarderieErrors> validatePostSave(final Parent parent) {
        final List<GarderieErrors> errors = new ArrayList<>();
        if(CollectionUtils.isEmpty(parent.getChildrenIds()))
            errors.add(GarderieErrors.MISSING_CHILDREN_IDS);
        return errors;
    }

    @Override
    public List<GarderieErrors> validate(Parent parent) {
        return null;
    }
}
