package com.metro.tram.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "tram_route")
public class TramRoute {
    //Номер маршрута
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //название от конечной остановки – до конечной остановки
    private String name;
    //внутренний код маршрута
    private String code;

}
