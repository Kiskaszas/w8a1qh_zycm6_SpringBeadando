package org.example.kaszmaginnovate.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Data
@NoArgsConstructor
@Table(name = "meccs")
public class Meccs implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String datum;

    @Column(nullable = false)
    private String kezdes;

    @Column(nullable = false)
    private int belepo;

    @Column(nullable = false)
    private String tipus;

    // Egy meccsre több belépés is tartozhat, így One-to-Many kapcsolat van
    @OneToMany(mappedBy = "meccs", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Belepes> belepesek = new ArrayList<>();
}