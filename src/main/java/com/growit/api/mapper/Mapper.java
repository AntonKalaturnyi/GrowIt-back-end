package com.growit.api.mapper;

import com.growit.api.domain.AbstractEntity;
import com.growit.api.dto.AbstractDto;

public interface Mapper<E extends AbstractEntity, D extends AbstractDto> {

    E toEntity(D dto);

    D toDto(E entity);
}
