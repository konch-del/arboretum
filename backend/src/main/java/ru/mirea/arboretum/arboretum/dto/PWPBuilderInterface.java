package ru.mirea.arboretum.arboretum.dto;

import ru.mirea.arboretum.arboretum.models.Param;

import java.util.List;

public interface PWPBuilderInterface {

    PWPBuilderInterface plantId(Long planId);

    PWPBuilderInterface name(String name);

    PWPBuilderInterface description(String description);

    PWPBuilderInterface params(List<Param> params);

    PlantWithParam build();
}
