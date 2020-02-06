package com.growit.api.repo;

import com.growit.api.domain.WorkSphere;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkSphereRepo extends CrudRepository<WorkSphere, Long> {

    WorkSphere findBySphereEngLike(String sphere);

    WorkSphere findBySphereUaLike(String sphere);

}
