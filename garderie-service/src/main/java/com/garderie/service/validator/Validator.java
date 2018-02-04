package com.garderie.service.validator;

import com.garderie.service.errors.GarderieErrors;

import java.util.List;

public interface Validator<T> {
    public List<GarderieErrors> validatePreSave(final T t);

    public List<GarderieErrors> validatePostSave(final T t);

    public List<GarderieErrors> validate(final T t);
}
