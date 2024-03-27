package com.example.fortesextrahourapi.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "address")
@Getter
@Setter
@NoArgsConstructor
public class Address {
    @Id
    private String id;

    private String city;

    private String neighborhood;
}
