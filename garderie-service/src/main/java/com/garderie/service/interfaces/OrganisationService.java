package com.garderie.service.interfaces;

import com.garderie.types.org.Organisation;
import com.garderie.types.security.auth.token.JwtTokenData;

public interface OrganisationService {

    public Organisation create(final Organisation organisation, final JwtTokenData jwtTokenData);

    public Organisation update(final Organisation organisation, final JwtTokenData jwtTokenData);

    public Organisation findOrganisationById(final String id);

    public void deletedById(final String id);

}
