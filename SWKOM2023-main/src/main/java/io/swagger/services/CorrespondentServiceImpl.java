package io.swagger.services;

import io.swagger.entity.CorrespondentEntity;
import io.swagger.model.Correspondent;
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

    @Autowired
    private CorrespondentRepository correspondentRepository;

    @Autowired
    private CorrespondentMapper correspondentMapper;

    @Override
    @Transactional(readOnly = true)
    public Correspondent getCorrespondentById(Long id) {
        Optional<CorrespondentEntity> correspondentEntityOptional = correspondentRepository.findById(id);
        return correspondentEntityOptional.map(correspondentMapper::toDTO).orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Correspondent> getAllCorrespondents() {
        List<CorrespondentEntity> correspondents = correspondentRepository.findAll();
        return correspondents.stream().map(correspondentMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public Correspondent createCorrespondent(Correspondent correspondent) {
        CorrespondentEntity correspondententity = correspondentMapper.toEntity(correspondent);
        CorrespondentEntity savedCorrespondent = correspondentRepository.save(correspondententity);
        return correspondentMapper.toDTO(savedCorrespondent);
    }

    @Override
    @Transactional
    public Correspondent updateCorrespondent(Long id, Correspondent correspondent) {
        if (!correspondentRepository.existsById(id)) {
            throw new RuntimeException("Correspondent with id " + id + " not found");
        }
        CorrespondentEntity correspondententity = correspondentMapper.toEntity(correspondent);
        correspondententity.setId(id);
        CorrespondentEntity updatedCorrespondent = correspondentRepository.save(correspondententity);
        return correspondentMapper.toDTO(updatedCorrespondent);
    }

    @Override
    @Transactional
    public void deleteCorrespondent(Long id) {
        correspondentRepository.deleteById(id);
    }
}
