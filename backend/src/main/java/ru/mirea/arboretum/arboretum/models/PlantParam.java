package ru.mirea.arboretum.arboretum.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "plantParam")
@NoArgsConstructor
public class PlantParam {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="plantparamid")
    private Long statusId;

    @Column(name = "plantid")
    private Long plant;

    @Column(name = "paramid")
    private Long param;
}
