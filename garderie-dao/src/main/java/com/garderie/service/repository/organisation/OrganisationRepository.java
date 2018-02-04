package com.garderie.service.repository.organisation;

import com.garderie.types.org.Organisation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrganisationRepository extends MongoRepository<Organisation, String> {

    public Organisation findById(final String id);

}
