package ru.mirea.arboretum.arboretum.models;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
public class PlantStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long statusId;

    private String param;

    private int value;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    @ManyToOne
    @JoinColumn(name = "plantId")
    private Plant plant;
}
