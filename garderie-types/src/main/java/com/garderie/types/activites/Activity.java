package com.garderie.types.activites;

import com.garderie.types.AbstractPersistable;

public class Activity extends AbstractPersistable {

    private ActivityType activityType;
    private String caption;
    private String childId;
    private String classId;
    private boolean isPublic;
    private String photoUrl;
    private String activityCreatedById;

    public ActivityType getActivityType() {
        return activityType;
    }

    public void setActivityType(final ActivityType activityType) {
        this.activityType = activityType;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(final String caption) {
        this.caption = caption;
    }

    public String getChildId() {
        return childId;
    }

    public void setChildId(final String childId) {
        this.childId = childId;
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(final String classId) {
        this.classId = classId;
    }

    public boolean isPublic() {
        return isPublic;
    }

    public void setPublic(final boolean aPublic) {
        isPublic = aPublic;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(final String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getActivityCreatedById() {
        return activityCreatedById;
    }

    public void setActivityCreatedById(final String activityCreatedById) {
        this.activityCreatedById = activityCreatedById;
    }
}
