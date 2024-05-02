package com.example.fortesextrahourapi.domain;

import com.example.fortesextrahourapi.enums.RoleEnum;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Document(collection = "employees")
@Getter
@Setter
@NoArgsConstructor
public class Employee implements UserDetails {

    @Id
    private String id;

    @NotBlank(message = "O campo name nao pode estar vazio!")
    private String name;

    @NotBlank(message = "O campo username nao pode estar vazio!")
    private String username;

    private RoleEnum role;

    @NotBlank(message = "O campo email nao pode estar vazio!")
    private String email;

    @NotBlank(message = "A password nao pode estar vazia!")
    @Length(min = 8, message = "A password deve ter no minimo 8 caracteres!")
    private String password;

    private String cellphone;

    private boolean active;

    private String registrationDate;

    private static final String ROLE_USER = "ROLE_USER";
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        if (this.role == RoleEnum.FUNCIONARIO) {
            return List.of(new SimpleGrantedAuthority("ROLE_FUNCIONARIO"), new SimpleGrantedAuthority(ROLE_USER));
        } else if (this.role == RoleEnum.GERENTE) {
            return List.of(new SimpleGrantedAuthority("ROLE_GERENTE"), new SimpleGrantedAuthority(ROLE_USER));
        } else if (this.role == RoleEnum.ENCARREGADO) {
            return List.of(new SimpleGrantedAuthority("ROLE_ENCARREGADO"), new SimpleGrantedAuthority(ROLE_USER));
        }
            else return List.of(new SimpleGrantedAuthority(ROLE_USER));
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
