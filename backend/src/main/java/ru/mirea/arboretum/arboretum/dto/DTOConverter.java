package ru.mirea.arboretum.arboretum.dto;

import ru.mirea.arboretum.arboretum.models.Param;
import ru.mirea.arboretum.arboretum.models.Plant;

import java.util.List;

public class DTOConverter {

    private DTOConverter(){

    }

    private static class ConverterHolder{
        public static final DTOConverter CONVERTER_INSTANCE = new DTOConverter();
    }

    public static DTOConverter getInstance(){
        return ConverterHolder.CONVERTER_INSTANCE;
    }

    public PlantWithParam convertToPlantWithParam(Plant plant, List<Param> params){
        PWPBuilderInterface builder = new PWPBuilder();
        return builder.plantId(plant.getPlantId())
                .name(plant.getName())
                .description(plant.getDescription())
                .params(params)
                .build();
    }
}
