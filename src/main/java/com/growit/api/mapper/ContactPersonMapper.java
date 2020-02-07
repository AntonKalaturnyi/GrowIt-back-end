package com.growit.api.mapper;

import com.growit.api.domain.ContactPerson;
import com.growit.api.dto.ContactPersonDto;
import com.growit.api.repo.ContactPersonRepo;
import com.growit.api.repo.ItnRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Objects;

@Component
public class ContactPersonMapper extends AbstractMapper<ContactPerson, ContactPersonDto> {

    private final ModelMapper mapper;
    private final ItnRepo itnRepo;

    @Autowired
    public ContactPersonMapper(ModelMapper mapper, ContactPersonRepo contactPersonRepo, ItnRepo itnRepo) {
        super(ContactPerson.class, ContactPersonDto.class);
        this.mapper = mapper;
        this.itnRepo = itnRepo;
    }

    @PostConstruct
    public void setupMapper() {
        mapper.createTypeMap(ContactPerson.class, ContactPersonDto.class)
                .addMappings(m -> m.skip(ContactPersonDto::setItnId)).setPostConverter(toDtoConverter());
        mapper.createTypeMap(ContactPersonDto.class, ContactPerson.class)
                .addMappings(m -> m.skip(ContactPerson::setItn)).setPostConverter(toEntityConverter());
    }

    @Override
    public void mapSpecificFields(ContactPerson source, ContactPersonDto destination) {
        destination.setItnId(getId(source));
    }

    private Long getId(ContactPerson source) {
        return Objects.isNull(source) || Objects.isNull(source.getId()) ? null : source.getItn().getId();
    }

    @Override
    void mapSpecificFields(ContactPersonDto source, ContactPerson destination) {
        destination.setItn(itnRepo.findById(source.getItnId()).orElse(null));
    }
}
