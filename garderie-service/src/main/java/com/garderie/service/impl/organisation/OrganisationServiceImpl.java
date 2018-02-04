package com.garderie.service.impl.organisation;

import com.garderie.service.interfaces.OrganisationService;
import com.garderie.service.repository.organisation.OrganisationRepository;
import com.garderie.types.org.Organisation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrganisationServiceImpl implements OrganisationService{

    @Autowired
    private OrganisationRepository organisationRepository;


    @Override
    public Organisation create(Organisation organisation) {
        return this.organisationRepository.save(organisation);
    }

    @Override
    public Organisation update(Organisation organisation) {
        return this.organisationRepository.save(organisation);
    }

    @Override
    public Organisation findOrganisationById(String id) {
        return this.organisationRepository.findById(id);
    }
}
