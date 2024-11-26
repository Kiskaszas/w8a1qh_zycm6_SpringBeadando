package org.example.kaszmaginnovate.service;

import org.example.kaszmaginnovate.model.Nezo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
public interface NezoService {
    Optional<Nezo> save(Nezo nezo);
    Optional<List<Nezo>> findAll();
    Optional<Nezo> findById(Long id);
    Optional<Nezo> update(Long id, Nezo nezoDetails);
    void delete(Long id);
    Page<Nezo> getAllNezo(Pageable of);
}
