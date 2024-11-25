package org.example.kaszmaginnovate.service;

import org.example.kaszmaginnovate.controller.response.MeccsResponse;
import org.example.kaszmaginnovate.model.Meccs;
import org.example.kaszmaginnovate.repository.MeccsRepository;
import org.hibernate.query.results.MissingSqlSelectionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class MeccsServiceImpl implements MeccsService {
    private final MeccsRepository meccsRepository;

    @Autowired
    public MeccsServiceImpl(MeccsRepository meccsRepository) {
        this.meccsRepository = meccsRepository;
    }

    @Override
    public Optional<Meccs> save(Meccs meccs) {
        return Optional.of(meccsRepository.save(meccs));
    }

    @Override
    public Optional<List<Meccs>> findAll() {
        return Optional.of(meccsRepository.findAll());
    }

    @Override
    public Optional<Meccs> findById(Long id) {
        return Optional.ofNullable(meccsRepository.findById(id).orElse(null));
    }

    @Override
    public Optional<Meccs> findByDatum(String datum) {
        return Optional.of(meccsRepository.findMeccsByDatum(datum));
    }

    @Override
    public Optional<Meccs> findByKezdes(String kezdes) {
        return Optional.of(meccsRepository.findMeccsByKezdes(kezdes));
    }

    @Override
    public Optional<Meccs> findByBelepo(Integer belepo) {
        return Optional.of(meccsRepository.findMeccsByBelepo(belepo));
    }


    @Override
    public Optional<Meccs> findByTipus(String tipus) {
        return Optional.of(meccsRepository.findMeccsByTipus(tipus));
    }

    @Override
        public MeccsResponse update(Long id, Meccs meccsDetails) {
            Optional<Meccs> meccsOpcional = findById(id);
            var meccs = meccsOpcional.isPresent() ? meccsOpcional.get() : null;
            if (meccs != null) {
                meccs.setDatum(meccsDetails.getDatum());
                meccs.setKezdes(meccsDetails.getKezdes());
                meccs.setBelepo(meccsDetails.getBelepo());
                meccs.setTipus(meccsDetails.getTipus());
                var updatedMeccs = Optional.of(meccsRepository.save(meccs)).get();
                return new MeccsResponse(
                        true,
                        "Sikeres Meccs frissírés",
                        null,
                        updatedMeccs);
            }
            return new MeccsResponse(
                    false,
                    "Sikertelen Meccs frissírés",
                    List.of("404 A meccs nem található"),
                    null);
        }

        @Override
        public void delete(Long id) {
            meccsRepository.deleteById(id);
        }

    @Override
    public Page<Meccs> getAllMeccs(Pageable pageable) {
        return meccsRepository.findAll(pageable);
    }
}
