package org.example.kaszmaginnovate.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "belepes")
public class Belepes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "nezoid", nullable = false)
    private Nezo nezo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "meccsid", nullable = false)
    private Meccs meccs;

    @Column(nullable = false)
    private String idopont;
}