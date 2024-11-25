package org.example.kaszmaginnovate.service;

import org.example.kaszmaginnovate.controller.response.MeccsResponse;
import org.example.kaszmaginnovate.model.Meccs;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
public interface MeccsService {
    Optional<Meccs> save(Meccs meccs);
    Optional<List<Meccs>> findAll();
    Optional<Meccs> findById(Long id);
    Optional<Meccs> findByDatum(String datum);
    Optional<Meccs> findByKezdes(String kezdes);
    Optional<Meccs> findByBelepo(Integer belepo);
    Optional<Meccs> findByTipus(String tipus);
    MeccsResponse update(Long id, Meccs meccsDetails);
    void delete(Long id);

    Page<Meccs> getAllMeccs(Pageable pageable);
}