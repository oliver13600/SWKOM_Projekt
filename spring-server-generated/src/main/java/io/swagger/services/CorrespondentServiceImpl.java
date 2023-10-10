package io.swagger.services;

import io.swagger.entity.CorrespondentEntity;
import io.swagger.services.dto.CorrespondentDTO;
import io.swagger.services.mapper.CorrespondentMapper;
import io.swagger.repository.CorrespondentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CorrespondentServiceImpl implements CorrespondentService {

    /*@Autowired
    private CorrespondentRepository correspondentRepository;

    @Autowired
    private CorrespondentMapper correspondentMapper;

    @Override
    @Transactional(readOnly = true)
    public CorrespondentDTO getCorrespondentById(Long id) {
        Optional<CorrespondentEntity> correspondentEntityOptional = correspondentRepository.findById(id);
        return correspondentEntityOptional.map(correspondentMapper::toDTO).orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CorrespondentDTO> getAllCorrespondents() {
        List<CorrespondentEntity> correspondents = correspondentRepository.findAll();
        return correspondents.stream().map(correspondentMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public CorrespondentDTO createCorrespondent(CorrespondentDTO correspondentDTO) {
        CorrespondentEntity correspondent = correspondentMapper.toEntity(correspondentDTO);
        CorrespondentEntity savedCorrespondent = correspondentRepository.save(correspondent);
        return correspondentMapper.toDTO(savedCorrespondent);
    }

    @Override
    @Transactional
    public CorrespondentDTO updateCorrespondent(Long id, CorrespondentDTO correspondentDTO) {
        if (!correspondentRepository.existsById(id)) {
            throw new RuntimeException("Correspondent with id " + id + " not found");
        }
        CorrespondentEntity correspondent = correspondentMapper.toEntity(correspondentDTO);
        correspondent.setId(id);
        CorrespondentEntity updatedCorrespondent = correspondentRepository.save(correspondent);
        return correspondentMapper.toDTO(updatedCorrespondent);
    }

    @Override
    @Transactional
    public void deleteCorrespondent(Long id) {
        correspondentRepository.deleteById(id);
    }*/
}
