package ru.mirea.arboretum.arboretum.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "plantstatus")
@NoArgsConstructor
public class PlantStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="plantstatusid")
    private Long statusId;

    @Column(name = "value1")
    private int value;

    @Column(name = "userid")
    private Long user;

    @Column(name = "plantid")
    private Long plant;
}
