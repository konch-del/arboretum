package ru.mirea.arboretum.arboretum.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import ru.mirea.arboretum.arboretum.models.Param;

import java.util.List;

@Getter
@Setter
@Builder
public class PlantWithParam {
    private Long plantId;

    private String name;

    private String description;

    private List<Param> params;
}
