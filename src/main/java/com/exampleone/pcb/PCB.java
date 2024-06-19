package com.exampleone.pcb;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "pcb")
public class PCB {
    @Id
    private String id;
    private String modelName;
    private String design;
    private EngineeringParameters engineeringParameters;
    private Logistics logistics;
}
