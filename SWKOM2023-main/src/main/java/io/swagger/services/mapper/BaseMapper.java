package io.swagger.services.mapper;

import io.swagger.services.dto.BaseDTO;
import org.mapstruct.Mapper;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface BaseMapper<D extends BaseDTO, E> {

    D toDTO(E entity);
    E toEntity(D dto);

    // Possibly other utility methods for lists, sets, etc.
    default List<D> toDTOList(List<E> entities) {
        return entities.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }
    default List<E> toEntityList(List<D> dtos) {
        return dtos.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }

}