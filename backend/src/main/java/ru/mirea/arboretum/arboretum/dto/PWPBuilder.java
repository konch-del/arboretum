package ru.mirea.arboretum.arboretum.dto;

import ru.mirea.arboretum.arboretum.models.Param;

import java.util.List;

public class PWPBuilder implements PWPBuilderInterface{
    private Long plantId;
    private String name;
    private String description;
    private List<Param> params;

    @Override
    public PWPBuilderInterface plantId(Long planId) {
        this.plantId = planId;
        return this;
    }

    @Override
    public PWPBuilderInterface name(String name) {
        this.name = name;
        return this;
    }

    @Override
    public PWPBuilderInterface description(String description) {
        this.description = description;
        return this;
    }

    @Override
    public PWPBuilderInterface params(List<Param> params) {
        this.params = params;
        return this;
    }

    @Override
    public PlantWithParam build() {
        PlantWithParam plantWithParam = new PlantWithParam(plantId, name, description, params);
        return plantWithParam;
    }
}
