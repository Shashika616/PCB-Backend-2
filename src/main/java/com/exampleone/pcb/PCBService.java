package com.exampleone.pcb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PCBService {

    @Autowired
    private PCBRepository pcbRepository;

    public List<PCB> allPCB() {
        return pcbRepository.findAll();
    }

    public Optional<PCB> findById(String ModelID) {
        return pcbRepository.findById(ModelID);
    }

    public PCB save(PCB pcb) {
        return pcbRepository.save(pcb);
    }

    public void deleteById(String ModelID) {
        pcbRepository.deleteById(ModelID);
    }

    public List<Customer> getCustomersByPCBId(String id) {
        Optional<PCB> pcbOptional = pcbRepository.findById(id);
        if (pcbOptional.isPresent() && pcbOptional.get().getLogistics() != null) {
            return pcbOptional.get().getLogistics().getCustomers();
        } else if (pcbOptional.isPresent()) {
            return new ArrayList<>(); // Return an empty list if logistics is null
        } else {
            throw new RuntimeException("PCB not found with id: " + id);
        }
    }


    public PCB addCustomerToPCB(String id, Customer customer) {
        Optional<PCB> pcbOptional = pcbRepository.findById(id);
        if (pcbOptional.isPresent()) {
            PCB pcb = pcbOptional.get();
            if (pcb.getLogistics() == null) {
                pcb.setLogistics(new Logistics());
            }
            pcb.getLogistics().getCustomers().add(customer);
            return pcbRepository.save(pcb);
        } else {
            throw new RuntimeException("PCB not found with id: " + id);
        }
    }

    public Customer getCustomerDetails(String pcbId, String customerId) {
        Optional<PCB> pcbOptional = pcbRepository.findById(pcbId);
        if (pcbOptional.isPresent() && pcbOptional.get().getLogistics() != null) {
            return pcbOptional.get().getLogistics().getCustomers().stream()
                    .filter(customer -> customer.getName().equals(customerId))
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("Customer not found with id: " + customerId));
        } else {
            throw new RuntimeException("PCB or Logistics not found with id: " + pcbId);
        }
    }

    public PCB updateCustomerDetails(String pcbId, String customerId, Customer updatedCustomer) {
        Optional<PCB> pcbOptional = pcbRepository.findById(pcbId);
        if (pcbOptional.isPresent()) {
            PCB pcb = pcbOptional.get();
            if (pcb.getLogistics() != null) {
                List<Customer> customers = pcb.getLogistics().getCustomers();
                for (int i = 0; i < customers.size(); i++) {
                    if (customers.get(i).getName().equals(customerId)) {
                        customers.set(i, updatedCustomer);
                        return pcbRepository.save(pcb);
                    }
                }
                throw new RuntimeException("Customer not found with id: " + customerId);
            } else {
                throw new RuntimeException("Logistics not found for PCB with id: " + pcbId);
            }
        } else {
            throw new RuntimeException("PCB not found with id: " + pcbId);
        }
    }

}
