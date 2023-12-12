package ru.mirea.arboretum.arboretum.models;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
public class Bucket {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long bucketId;

    @OneToOne
    private Plant plant;

    private String name;
}
