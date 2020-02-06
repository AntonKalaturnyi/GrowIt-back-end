package com.growit.api.repo;

import com.growit.api.domain.ContactPerson;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactPersonRepo extends CrudRepository<ContactPerson, Long> {

}
