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
public class Plant {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long plantId;

    private String name;

    private String description;

    @OneToMany
    private List<Param> param;

    @OneToMany
    private List<PlantStatus> plantStatuses;
}
