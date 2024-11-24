package org.example.kaszmaginnovate.repository;

import org.example.kaszmaginnovate.model.Meccs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MeccsRepository extends JpaRepository<Meccs, Long> {
    Meccs findMeccsByDatum(String datum);
    Meccs findMeccsByKezdes(String kezdes);
    Meccs findMeccsByBelepo(Integer belepok);
    Meccs findMeccsByTipus(String tipus);}
