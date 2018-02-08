package com.garderie.types;


import org.apache.commons.collections4.CollectionUtils;

import java.util.*;

public class GarderieResponse {
    private Map<String, Object> dataMap;
    private boolean hasErrors;
    private List<String> errors;
    private String nextStep;

    public String getNextStep() {
        return nextStep;
    }

    public void setNextStep(String nextStep) {
        this.nextStep = nextStep;
    }

    public void addData(final String key, final Object value) {
        if(Objects.isNull(this.dataMap)) {
            this.dataMap = new HashMap<>();
        }
        this.dataMap.put(key, value);
    }

    public Object getData(final String key) {
        if(Objects.isNull(this.dataMap)) {
            return null;
        }
        return this.dataMap.get(key);
    }

    public void addErrors(String error ){
        if(CollectionUtils.isEmpty(errors)){
            this.errors = new ArrayList<>();
        }
        this.errors.add(error);
        this.hasErrors = true;
    }

    public Map<String, ?> getDataMap() {
        return dataMap;
    }

    public void setDataMap(Map<String, Object> dataMap) {
        this.dataMap = dataMap;
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
