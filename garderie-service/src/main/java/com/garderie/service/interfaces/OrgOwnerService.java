package com.garderie.service.interfaces;

import com.garderie.types.org.OrgOwner;
import com.garderie.types.security.auth.token.JwtTokenData;

public interface OrgOwnerService {

    public OrgOwner create(final OrgOwner orgOwner, final JwtTokenData jwtTokenData);
    public OrgOwner update(final OrgOwner orgOwner,final JwtTokenData jwtTokenData);
    public OrgOwner findByEmailId( final String emailId );
    public OrgOwner findByOrgOwnerId( final String id );
    public void deleteByOrgOwnerId( final String id);
    public OrgOwner createOrgOwnerInternally(final OrgOwner orgOwner);
    public OrgOwner updateOrgOwnerOrgIdInternally(final String orgownerId, final String orgId);

}
