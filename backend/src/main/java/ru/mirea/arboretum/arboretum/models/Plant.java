package ru.mirea.arboretum.arboretum.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "plant")
@NoArgsConstructor
public class Plant {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "plantid")
    private Long plantId;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

}
