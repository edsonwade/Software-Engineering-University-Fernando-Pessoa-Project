package ufp.esof.project.mapper;

import org.mapstruct.*;
import ufp.esof.project.dto.CollegeDto;
import ufp.esof.project.persistence.model.College;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface CollegeMapper {
    College toEntity(CollegeDto collegeDto);

    @AfterMapping
    default void linkDegrees(@MappingTarget College college) {
        college.getDegrees().forEach(degree -> degree.setCollege(college));
    }

    CollegeDto toDto(College college);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    College partialUpdate(CollegeDto collegeDto, @MappingTarget College college);
}