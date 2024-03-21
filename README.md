# Dia 01 - 19/03/24

### Iniciando o Backend da Aplicação

**Criado o projeto Spring utilizando o Spring Initializr com as seguintes dependências:**

1. **Spring Web**: Essencial para construir sua API RESTful, permitindo a criação de controladores, mapeamento de rotas e tratamento de requisições/respostas HTTP.
2. **Spring Data MongoDB**: Facilita a interação com o MongoDB, oferecendo abstrações para realizar operações CRUD, além de consultas personalizadas, sem a necessidade de escrever muito código boilerplate.
3. **Spring Security**: Fornece funcionalidades de autenticação e autorização. É altamente configurável e se integrará bem ao gerenciar diferentes níveis de acesso entre os atores do seu sistema (Encarregado, Técnico, Gestor, etc.).
    1. Adicionato também as depêndencias necessárias para realizar a autenticação com Tokens JWT `spring-security-config | jakarta.validation-api | java-jwt`
4. **Spring Boot DevTools**: Oferece recursos como recarregamento automático de aplicações e configurações de desenvolvimento, facilitando o processo de desenvolvimento.
5. **Lombok**: Reduz o boilerplate em seu código Java, automatizando a geração de getters, setters, métodos equals, hashCode e toString com simples anotações.
6. **Validation**: Suporte à validação de beans, útil para garantir que os dados recebidos nas suas APIs atendam a determinados critérios antes de serem processados ou persistidos.

Feito configuração inicial do banco de dados MongoDB a partir do MongoDBCompass

Criado primeiro banco de dados com o nome “extra-hours-management”

Criado primeira collection com o nome “employees”

Configuração Inicial:

- Criado o package config.mongo
- Feito a configuração Inicial da classe “MongoDBConfig”

```java
package com.example.fortesextrahourapi.config.mongo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;

@Configuration
public class MongoDBConfig {

    @Bean
    public MongoDatabaseFactory mongoConfigure(){
        return new SimpleMongoClientDatabaseFactory("mongodb://localhost:27017/extra-hours-management");
    }

    @Bean
    public MongoTemplate mongoTemplate(){
        return new MongoTemplate(mongoConfigure());
    }
}
```

- Adicionado a Annotation @EnableMongoRepositories o arquivo de entrada da aplicação “FortesExtrahourApiApplication.java”

```java
@EnableMongoRepositories
@SpringBootApplication
public class FortesExtrahourApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(FortesExtrahourApiApplication.class, args);
	}

}
```

Criando a camada de Domínio

- Criado o domínio Employee (domain.employee)
- Criado a classe Employee, com todos os campos necessários

```java
package com.example.fortesextrahourapi.domain;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document(collection = "employees")
@Getter
@Setter
@NoArgsConstructor
public class Employee {
    @Id
    private String id;
    @NotBlank(message = "O nome nao pode estar vazio!")
    private String nome;
    private Cargo cargo;
    @NotBlank(message = "O email nao pode estar vazio!")
    private String email;
    @NotBlank(message = "A senha nao pode estar vazia!")
    @Length(min = 8, message = "A senha deve ter no minimo 8 caracteres!")
    private String senha; // lembrar de criptografar
    private String telefone;
    private boolean ativo;
    private LocalDate dataDeCadastro;
}
```

- Adicionado as Annotations @Getter e @Setter do Lombok para criar os getters e setters dos campos em tempo de execução e para abstrair código
- Adicionado as Anottations @NoArgsConstructor para criar construtor sem argumentos para a classe Employee
- Adicionado a Annotation *`@Document*(collection = "employees")` para definir que a classe Employee representará a entidade do domínio employees

**Iniciando configuração da autenticação com Token JWT**

- Criado o Token Service, dentro do package Config, para configurar a criação do Token
- Criado a função getSubject para capturar o usuário do funcionário para validar o token

```java
package com.example.fortesextrahourapi.config.tocken;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.example.fortesextrahourapi.domain.Employee;
import com.example.fortesextrahourapi.exceptions.CustomTokenCreationException;
import com.example.fortesextrahourapi.exceptions.CustomTokenValidationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TokenService {
    @Value("${jwt.secret}")
    private String secret;
    @Value("${jwt.expiration}")
    private Long expirationTime;
    public String generateToken(Employee employee) {
        try {
            return JWT.create()
                    .withIssuer("ExtraHoursAPI")
                    .withSubject(employee.getNome())
                    .withClaim("id", employee.getId())
                    .withExpiresAt(new Date(System.currentTimeMillis() + expirationTime))
                    .sign(Algorithm.HMAC256(secret));
        } catch (JWTCreationException e) {
            throw new CustomTokenCreationException("Falha ao criar o Token!", e);
        }
    }

    public String getSubject(String token) {
        try {
            if (token == null || token.isEmpty()) {
                return null;
            }

            if (isTokenIsEmpty(token)) {
                return null;
            }

            return JWT.require(Algorithm.HMAC256("${jwt.secret}"))
                    .withIssuer("ExtraHoursAPI")
                    .build().verify(token).getSubject();
        } catch (JWTCreationException e) {
            throw new CustomTokenValidationException("Falha ao validar o Token!", e);
        }
    }
}
```
