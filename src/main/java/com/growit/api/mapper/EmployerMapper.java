package com.growit.api.mapper;

import com.growit.api.domain.Employer;
import com.growit.api.dto.EmployerDto;
import com.growit.api.repo.AddressRepo;
import com.growit.api.repo.WorkSphereRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;
import java.util.Objects;

@Component
public class EmployerMapper extends AbstractMapper<Employer, EmployerDto> {

    private final ModelMapper mapper;
    private final WorkSphereRepo workSphereRepo;
    private final AddressRepo addressRepo;

    @Autowired
    public EmployerMapper(ModelMapper mapper, WorkSphereRepo workSphereRepo, AddressRepo addressRepo) {
        super(Employer.class, EmployerDto.class);
        this.mapper = mapper;
        this.workSphereRepo = workSphereRepo;
        this.addressRepo = addressRepo;
    }

    @PostConstruct
    public void setupMapper() {
        mapper.createTypeMap(Employer.class, EmployerDto.class)
                .addMappings(m -> m.skip(EmployerDto::setWorkSphereId)).setPostConverter(toDtoConverter())
                .addMappings(m -> m.skip(EmployerDto::setJobAddressId)).setPostConverter(toDtoConverter());

        mapper.createTypeMap(EmployerDto.class, Employer.class)
                .addMappings(m -> m.skip(Employer::setWorkSphere)).setPostConverter(toEntityConverter())
                .addMappings(m -> m.skip(Employer::setJobAddress)).setPostConverter(toEntityConverter());
    }

    @Override
    public void mapSpecificFields(Employer source, EmployerDto destination) {
        destination.setJobAddressId(getJobAddressId(source));
        destination.setWorkSphereId(getWorkSphereId(source));
    }

    @Override
    void mapSpecificFields(EmployerDto source, Employer destination) {
        destination.setWorkSphere(workSphereRepo.findById(source.getWorkSphereId()).orElse(null));
        destination.setJobAddress(addressRepo.findById(source.getJobAddressId()).orElse(null));
    }

    private Long getWorkSphereId(Employer source) {
        return Objects.isNull(source) || Objects.isNull(source.getId()) ? null : source.getWorkSphere().getId();
    }

    private Long getJobAddressId(Employer source) {
        return Objects.isNull(source) || Objects.isNull(source.getId()) ? null : source.getJobAddress().getId();
    }
}
