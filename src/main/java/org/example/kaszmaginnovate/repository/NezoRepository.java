package org.example.kaszmaginnovate.repository;

import org.example.kaszmaginnovate.model.Nezo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NezoRepository extends JpaRepository<Nezo, Long> {
}
