/*
package com.growit.api.mapper;

import com.growit.api.domain.Employment;
import com.growit.api.dto.EmploymentDto;
import com.growit.api.repo.AddressRepo;
import com.growit.api.repo.WorkSphereRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;
import java.util.Objects;

@Component
public class EmploymentMapper extends AbstractMapper<Employment, EmploymentDto> {

    private final ModelMapper mapper;
    private final WorkSphereRepo workSphereRepo;
    private final AddressRepo addressRepo;

    @Autowired
    public EmploymentMapper(ModelMapper mapper, WorkSphereRepo workSphereRepo, AddressRepo addressRepo) {
        super(Employment.class, EmploymentDto.class);
        this.mapper = mapper;
        this.workSphereRepo = workSphereRepo;
        this.addressRepo = addressRepo;
    }

    @PostConstruct
    public void setupMapper() {
        mapper.createTypeMap(Employment.class, EmploymentDto.class)
                .addMappings(m -> m.skip(EmploymentDto::setWorkSphereId)).setPostConverter(toDtoConverter())
                .addMappings(m -> m.skip(EmploymentDto::setJobAddressId)).setPostConverter(toDtoConverter());

        mapper.createTypeMap(EmploymentDto.class, Employment.class)
                .addMappings(m -> m.skip(Employment::setWorkSphere)).setPostConverter(toEntityConverter())
                .addMappings(m -> m.skip(Employment::setJobAddress)).setPostConverter(toEntityConverter());
    }

    @Override
    public void mapSpecificFields(Employment source, EmploymentDto destination) {
        destination.setJobAddressId(getJobAddressId(source));
        destination.setWorkSphereId(getWorkSphereId(source));
    }

    @Override
    void mapSpecificFields(EmploymentDto source, Employment destination) {
        destination.setWorkSphere(workSphereRepo.findById(source.getWorkSphereId()).orElse(null));
        destination.setJobAddress(addressRepo.findById(source.getJobAddressId()).orElse(null));
    }

    private Long getWorkSphereId(Employment source) {
        return Objects.isNull(source) || Objects.isNull(source.getId()) ? null : source.getWorkSphere().getId();
    }

    private Long getJobAddressId(Employment source) {
        return Objects.isNull(source) || Objects.isNull(source.getId()) ? null : source.getJobAddress().getId();
    }
}
*/
