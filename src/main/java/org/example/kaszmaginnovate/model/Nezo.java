package org.example.kaszmaginnovate.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name = "nezo")
public class Nezo implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nev;

    @Column(nullable = false)
    @Convert(converter = BooleanConverter.class)
    private boolean ferfi;

    @Column(nullable = false)
    @Convert(converter = BooleanConverter.class)
    private boolean berletes;

    // Egy néző több belépést is létrehozhat, így One-to-Many kapcsolat van
    @OneToMany(mappedBy = "nezo", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Belepes> belepesek = new ArrayList<>();
}