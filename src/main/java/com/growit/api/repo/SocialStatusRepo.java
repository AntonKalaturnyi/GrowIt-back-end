package com.growit.api.repo;

import com.growit.api.domain.SocialStatus;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SocialStatusRepo extends CrudRepository<SocialStatus, Long> {

    SocialStatus findByStatusEngLike(String status);

    SocialStatus findByStatusUaLike(String status);

}
