package com.example.fortesextrahourapi.repositories;

import com.example.fortesextrahourapi.domain.ExtraHoursProgramations;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ExtraHoursRepository extends MongoRepository<ExtraHoursProgramations, String> {
    Optional<ExtraHoursProgramations> findById(String id);
}
