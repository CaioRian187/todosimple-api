package com.caiorian.todosimple.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="user")
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private Long id;

    @Column(name="username", length = 100, nullable = false, unique = true)
    @NotNull
    @NotEmpty
    @Size(min = 2, max = 100)
    private String username;

    @Column(name="password", length = 60, nullable = false)
    @NotNull
    @NotEmpty
    @Size(min = 8, max = 60)
    private String password;

}
