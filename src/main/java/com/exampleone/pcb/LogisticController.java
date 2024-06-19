package com.exampleone.pcb;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/logistics")
public class LogisticController {

    @Autowired
    private PCBService pcbService;

    @GetMapping("/pcbs")
    public List<PCB> getAllPCBs() {
        return pcbService.allPCB();
    }

    @GetMapping("/pcbs/{id}")
    public Optional<PCB> getPCBById(@PathVariable String id) {
        return pcbService.findById(id);
    }

    @GetMapping("/pcbs/{id}/customers")
    public List<Customer> getCustomersByPCBId(@PathVariable String id) {
        return pcbService.getCustomersByPCBId(id);
    }

    @PostMapping("/pcbs/{id}/customers")
    public PCB addCustomerToPCB(@PathVariable String id, @RequestBody Customer customer) {
        return pcbService.addCustomerToPCB(id, customer);
    }

    @GetMapping("/pcbs/{pcbId}/customers/{customerId}")
    public Customer getCustomerDetails(@PathVariable String pcbId, @PathVariable String customerId) {
        return pcbService.getCustomerDetails(pcbId, customerId);
    }

    @PutMapping("/pcbs/{pcbId}/customers/{customerId}")
    public PCB updateCustomerDetails(@PathVariable String pcbId, @PathVariable String customerId, @RequestBody Customer customer) {
        return pcbService.updateCustomerDetails(pcbId, customerId, customer);
    }

}
