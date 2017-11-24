package com.garderie.service.controller;

import com.garderie.service.impl.ChildService;
import com.garderie.types.user.types.Child;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/child")
public class ChildController {

    @Autowired
    private ChildService childService;

    @RequestMapping(method = RequestMethod.POST)
    public Child register(@RequestBody final Child child){
        return this.childService.create(child);
    }




}
