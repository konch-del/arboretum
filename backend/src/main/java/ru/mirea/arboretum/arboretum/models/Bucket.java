package ru.mirea.arboretum.arboretum.models;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@Table(name = "bucket")
public class Bucket {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "bucketId")
    private Long bucketId;

    @OneToOne
    private Plant plant;

    @Column(name = "name")
    private String name;
}
