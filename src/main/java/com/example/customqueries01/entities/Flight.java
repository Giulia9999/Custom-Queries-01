package com.example.customqueries01.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "flights")
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String description;
    private String fromAirport;
    private String toAirport;
    private flightStatusEnum status;
}
