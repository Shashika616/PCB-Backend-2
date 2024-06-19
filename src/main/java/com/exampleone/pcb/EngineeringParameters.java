package com.exampleone.pcb;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EngineeringParameters {
    private String materialType;
    private int numberOfLayers;
    private double laminateThickness;
    private String coating;
    private String solderMaskColor;
}
