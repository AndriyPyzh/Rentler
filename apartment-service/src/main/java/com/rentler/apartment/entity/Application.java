package com.rentler.apartment.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "applications")
public class Application {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private LocalDate creationDate;

    @Column(nullable = false)
    private String owner;

    @Column(nullable = false)
    private Integer price;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Apartment apartment;

    @PrePersist
    public void prePersist() {
        creationDate = LocalDate.now();
    }
}

