package com.garderie.types.user.types;

import com.garderie.types.user.info.User;
import com.garderie.types.user.info.UserType;

import java.util.List;

public class Teacher extends User {

    private String orgId;
    private List<String> childrenIds;
    private List<String> classRoomIds;
    private UserType userType;

    public UserType getUserType() {
        return this.userType;
    }

    public void setUserType(final UserType userType) {
        this.userType = UserType.TEACHER;
    }

    public String getOrgId() {
        return this.orgId;
    }

    public void setOrgId(final String orgId) {
        this.orgId = orgId;
    }

    public List<String> getChildrenIds() {
        return this.childrenIds;
    }

    public void setChildrenIds(final List<String> childrenIds) {
        this.childrenIds = childrenIds;
    }

    public List<String> getClassRoomIds() {
        return this.classRoomIds;
    }

    public void setClassRoomIds(final List<String> classRoomIds) {
        this.classRoomIds = classRoomIds;
    }
}
