package com.garderie.service.repository;

import com.garderie.types.org.OrgOwner;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrgOwnerRepository extends MongoRepository<OrgOwner, String> {

    public OrgOwner findByEmailId(final String emailId);

    public OrgOwner findByOrgId(final String orgId);

}
