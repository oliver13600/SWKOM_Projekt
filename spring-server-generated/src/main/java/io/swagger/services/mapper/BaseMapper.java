package io.swagger.services.mapper;

import io.swagger.services.dto.BaseDTO;
import org.mapstruct.Mapper;
import java.util.List;
@Mapper(componentModel = "spring")
public interface BaseMapper<D extends BaseDTO, E> {

    D toDTO(E entity);
    E toEntity(D dto);

    // Possibly other utility methods for lists, sets, etc.
    List<D> toDTOList(List<E> entities);
    List<E> toEntityList(List<D> dtos);

}