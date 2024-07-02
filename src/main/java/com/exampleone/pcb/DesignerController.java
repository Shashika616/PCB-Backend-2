package com.exampleone.pcb;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("api/designers")
public class DesignerController {

    @Autowired

    private PCBService pcbService;

    private static final Logger logger = LoggerFactory.getLogger(DesignerController.class);

    @GetMapping("/pcbs")
    public List<PCB> getallPCB(){
         try {
             return pcbService.allPCB();
         }catch (Exception e){
             logger.error("Error occurred while fetching PCBs", e);
             return List.of();
         }
    }

    @PostMapping("/pcbs")
    public ResponseEntity<PCB> createPCB(@RequestBody PCB pcb) {
        PCB createdPCB = pcbService.save(pcb);
        return new ResponseEntity<>(createdPCB, HttpStatus.CREATED);
    }

    @PutMapping("/pcbs/{id}/design")
    public ResponseEntity<PCB> updatePCBDesign(@PathVariable String id, @RequestBody Map<String, String> request) {
        Optional<PCB> pcb = pcbService.findById(id);
        if(pcb.isPresent()){
            PCB existingPCB = pcb.get();
            String design = request.get("design");
            existingPCB.setDesign(design);
            return ResponseEntity.ok(pcbService.save(existingPCB));
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/pcbs/{id}/design")
    public ResponseEntity<String> getPCBDesign(@PathVariable String id) {
        Optional<PCB> pcb = pcbService.findById(id);
        return pcb.map(value -> ResponseEntity.ok(value.getDesign())).orElseGet(() -> ResponseEntity.notFound().build());
    }

}

