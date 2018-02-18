package com.garderie.types.org;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.garderie.types.AbstractPersistable;
import com.garderie.types.user.info.Address;
import org.apache.commons.collections4.CollectionUtils;

public class Classroom extends AbstractPersistable{
	private String orgId;
	private String name;
	private Set<String> studentIds;

	public String getOrgId() {
		return this.orgId;
	}
	public void setOrgId(final String orgId) {
		this.orgId = orgId;
	}
	public String getName() {
		return this.name;
	}
	public void setName(final String name) {
		this.name = name;
	}
	public Set<String> getStudentIds() {
		return studentIds;
	}

	public void setStudentIds(Set<String> studentIds) {
		this.studentIds = studentIds;
	}

	public void addStudentId( final String studentId ){
		if(CollectionUtils.isEmpty(this.studentIds)) {
			this.studentIds = new HashSet<>();
		}
		this.studentIds.add(studentId);
	}

	public void addStudentIds( final Set<String> studentIds) {
		if(CollectionUtils.isEmpty(this.studentIds)){
			this.studentIds = new HashSet<>();
		}
		this.studentIds.addAll(studentIds);
	}
}
