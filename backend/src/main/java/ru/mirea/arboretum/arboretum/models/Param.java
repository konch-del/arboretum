package ru.mirea.arboretum.arboretum.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "param")
@NoArgsConstructor
public class Param {

    @Id
    @Column(name = "paramid")
    private Long paramId;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "value1")
    private int value;
}
