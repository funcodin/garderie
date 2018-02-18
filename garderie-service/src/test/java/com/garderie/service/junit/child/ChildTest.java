package com.garderie.service.junit.child;

import com.garderie.service.interfaces.ChildService;
import com.garderie.service.junit.BaseJUnitTest;
import com.garderie.service.repository.ChildRepository;
import com.garderie.types.user.types.Child;
import org.apache.commons.collections4.CollectionUtils;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

public class ChildTest extends BaseJUnitTest {

    @Autowired
    private ChildRepository childRepository;



    @Test
    public void testGetAllChildrensInClassroom() {

        final List<String> childrenIds = new ArrayList<>();
        Set<String> classroomIds = new HashSet<>();
        classroomIds.add("xyz");
        classroomIds.add("xyzefggg");

        Child child = new Child();
        child.setFirstName("R");
        child.setLastName("D");
        child.setOrgId("abcdefg");
        child.setClassroomIds(classroomIds);
        child.setId(UUID.randomUUID().toString());
        childrenIds.add(child.getId());

        this.childRepository.save(child);

        classroomIds.clear();
        classroomIds.add("xyz");
        classroomIds.add("xyzefggggg");

        child.setId(UUID.randomUUID().toString());

        childrenIds.add(child.getId());
        this.childRepository.save(child);

        Set<Child> childrens = this.childRepository.findChildByClassroomIdsIn("xyz");

        Assert.assertTrue(CollectionUtils.isNotEmpty(childrens));
        Assert.assertTrue(childrens.size() == 2);
        childrens.clear();
        childrens = this.childRepository.findChildByClassroomIdsIn("xyzefggg");

        Assert.assertTrue(CollectionUtils.isNotEmpty(childrens));

        for(String id : childrenIds ){
            this.childRepository.delete(id);
        }

    }


    @Test
    public void testGetAllChildrensInOrg() {
        final List<String> childrenIds = new ArrayList<>();
        Set<String> classroomIds = new HashSet<>();
        classroomIds.add("xyz");
        classroomIds.add("xyzefggg");

        Child child = new Child();
        child.setFirstName("R");
        child.setLastName("D");
        child.setOrgId("abcdefg");
        child.setClassroomIds(classroomIds);
        child.setId(UUID.randomUUID().toString());
        childrenIds.add(child.getId());

        this.childRepository.save(child);

        classroomIds.clear();
        classroomIds.add("xyz");
        classroomIds.add("xyzefggggg");

        child.setId(UUID.randomUUID().toString());

        childrenIds.add(child.getId());
        this.childRepository.save(child);

        child.setId(UUID.randomUUID().toString());
        child.setOrgId("1234");
        childrenIds.add(child.getId());
        this.childRepository.save(child);

        Set<Child> childrens = this.childRepository.findByOrgId("1234");

        Assert.assertTrue(childrens.size() == 1);

        for(String id : childrenIds ){
            this.childRepository.delete(id);
        }


    }



}
