package io.swagger.services.mapper;

import io.swagger.model.Correspondent;
import io.swagger.services.dto.CorrespondentDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CorrespondentMapper extends BaseMapper<CorrespondentDTO, Correspondent> {
    // No additional methods needed unless there are other specific transformations for Correspondent.
}