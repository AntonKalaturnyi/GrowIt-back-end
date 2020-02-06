package com.growit.api.repo;

import com.growit.api.domain.HomeOwnership;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HomeOwnershipRepo extends CrudRepository<HomeOwnership, Long> {

    HomeOwnership findByHomeOwnershipEngLike(String eng);

    HomeOwnership findByHomeOwnershipUaLike(String ua);
}

