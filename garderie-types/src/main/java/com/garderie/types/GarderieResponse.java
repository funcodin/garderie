package com.garderie.types;


import org.apache.commons.collections4.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

public class GarderieResponse<T> {
    private List<T> data;
    private boolean hasErrors;
    private List<String> errors;
    private String nextStep;

    public String getNextStep() {
        return nextStep;
    }

    public void setNextStep(String nextStep) {
        this.nextStep = nextStep;
    }

    public void addData(T t) {
        if (CollectionUtils.isEmpty(this.data)) {
            this.data = new ArrayList<>();
        }
        this.data.add(t);
    }

    public void addErrors(String error ){
        if(CollectionUtils.isEmpty(errors)){
            this.errors = new ArrayList<>();
        }
        this.errors.add(error);
        this.hasErrors = true;
    }
    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public boolean isHasErrors() {
        return hasErrors;
    }

    public void setHasErrors(boolean hasErrors) {
        this.hasErrors = hasErrors;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }
}
