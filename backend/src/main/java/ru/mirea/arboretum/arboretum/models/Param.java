package ru.mirea.arboretum.arboretum.models;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@Table(name = "param")
public class Param {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "paramid")
    private Long paramId;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "value")
    private int value;

    @ManyToOne
    @JoinColumn(name = "plantId")
    private Plant plant;
}
