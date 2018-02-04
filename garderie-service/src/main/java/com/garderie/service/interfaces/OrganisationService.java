package com.garderie.service.interfaces;

import com.garderie.types.org.Organisation;

public interface OrganisationService {

    public Organisation create( final Organisation organisation);
    public Organisation update(final Organisation organisation);
    public Organisation findOrganisationById( final String id );

}
