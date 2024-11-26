package org.example.kaszmaginnovate.service;

import org.example.kaszmaginnovate.model.Nezo;
import org.example.kaszmaginnovate.repository.NezoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NezoServiceImpl implements NezoService {

    private final NezoRepository nezoRepository;

    public NezoServiceImpl(final NezoRepository nezoRepository) {
        this.nezoRepository = nezoRepository;
    }

    @Override
    public Optional<Nezo> save(Nezo nezo) {
        return Optional.of(nezoRepository.save(nezo));
    }

    @Override
    public Optional<List<Nezo>> findAll() {
        return Optional.of(nezoRepository.findAll());
    }

    @Override
    public Optional<Nezo> findById(Long id) {
        return nezoRepository.findById(id);
    }

    @Override
    public Optional<Nezo> update(Long id, Nezo nezoDetails) {
        Optional<Nezo> nezoOpcional = findById(id);
        Nezo nezo = nezoOpcional.orElse(null);
        if (nezo != null) {
            nezo.setNev(nezoDetails.getNev());
            nezo.setFerfi(nezoDetails.isFerfi());
            nezo.setBerletes(nezoDetails.isBerletes());
            return Optional.of(nezoRepository.save(nezo));
        }
        return Optional.empty();
    }

    @Override
    public void delete(Long id) {
        nezoRepository.deleteById(id);
    }

    @Override
    public Page<Nezo> getAllNezo(Pageable pageable) {
        return nezoRepository.findAll(pageable);
    }
}