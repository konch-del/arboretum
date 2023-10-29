package ru.mirea.arboretum.arboretum.models;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
public class Param {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long paramId;

    private String name;

    private String description;

    private int value;

    @ManyToOne
    @JoinColumn(name = "plantId")
    private Plant plant;
}
