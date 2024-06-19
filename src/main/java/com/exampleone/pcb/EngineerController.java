package com.exampleone.pcb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/engineers")
public class EngineerController {

    @Autowired
    private PCBService pcbService;

    @GetMapping("/pcbs")
    public List<PCB> getallPCB(){
        return pcbService.allPCB();
    }

    @GetMapping("/pcbs/{id}/design")
    public ResponseEntity<String> getPCBDesign(@PathVariable String id) {
        Optional<PCB> pcb = pcbService.findById(id);
        return pcb.map(value -> ResponseEntity.ok(value.getDesign())).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/pcbs/{id}/engineering-parameters")
    public ResponseEntity<EngineeringParameters> getEngineeringParameters(@PathVariable String id) {
        Optional<PCB> pcb = pcbService.findById(id);
        return pcb.map(value -> ResponseEntity.ok(value.getEngineeringParameters())).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/pcbs/{id}/engineering-parameters")
    public ResponseEntity<PCB> updateEngineeringParameters(@PathVariable String id, @RequestBody EngineeringParameters engineeringParameters) {
        Optional<PCB> pcb = pcbService.findById(id);
        if (pcb.isPresent()) {
            PCB existingPCB = pcb.get();
            existingPCB.setEngineeringParameters(engineeringParameters);
            return ResponseEntity.ok(pcbService.save(existingPCB));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
