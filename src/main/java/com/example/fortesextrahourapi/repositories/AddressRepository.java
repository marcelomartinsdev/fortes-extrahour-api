package com.example.fortesextrahourapi.repositories;

import com.example.fortesextrahourapi.domain.Address;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends MongoRepository<Address,Long> {
}
