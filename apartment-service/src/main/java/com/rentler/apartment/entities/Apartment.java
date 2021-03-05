package com.rentler.apartment.entities;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Data
@Entity
public class Apartment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;

}