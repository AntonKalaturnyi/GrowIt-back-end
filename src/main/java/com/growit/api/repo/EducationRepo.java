package com.growit.api.repo;

import com.growit.api.domain.Education;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EducationRepo extends CrudRepository<Education, Long> {
}
