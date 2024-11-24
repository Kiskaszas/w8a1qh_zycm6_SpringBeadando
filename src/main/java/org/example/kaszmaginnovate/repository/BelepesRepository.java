package org.example.kaszmaginnovate.repository;

import org.example.kaszmaginnovate.model.Belepes;
import org.example.kaszmaginnovate.model.Meccs;
import org.example.kaszmaginnovate.model.Nezo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BelepesRepository extends JpaRepository<Belepes, Long> {
    List<Belepes> findBelepesByMeccs(Meccs id);
    List<Belepes> findBelepesByNezo(Nezo id);
    List<Belepes> findBelepesByIdopont(String idopont);
    void deleteBelepesByNezoAndMeccsAndIdopont(Nezo nezo, Meccs meccs, String idopont);

    void deleteByMeccs(Meccs meccs);
}
