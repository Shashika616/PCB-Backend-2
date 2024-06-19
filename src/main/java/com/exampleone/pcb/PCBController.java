package com.exampleone.pcb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/pcbtestpage")

public class PCBController {

    @Autowired
    private PCBService pcbService;

    @GetMapping
    public ResponseEntity<List<PCB>> getallPCBs() {
       return new ResponseEntity<List<PCB>>(pcbService.allPCB(), HttpStatus.OK);
    }
}
