package com.example.fortesextrahourapi.repositories;

import com.example.fortesextrahourapi.domain.ExtraHoursProgramations;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ExtraHoursRepository extends MongoRepository<ExtraHoursProgramations, String> {
}
