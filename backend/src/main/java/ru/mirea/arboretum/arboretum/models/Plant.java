package ru.mirea.arboretum.arboretum.models;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@Table(name = "plant")
public class Plant {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "plantid")
    private Long plantId;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @OneToMany
    private List<Param> param;

    @OneToMany
    private List<PlantStatus> plantStatuses;
}
