package com.exampleone.pcb;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PCBRepository extends MongoRepository<PCB, String> {
}
