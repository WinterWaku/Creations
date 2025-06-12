package com.example.demo.model;

import jakarta.persistence.*;
import org.springframework.data.annotation.Id;

@Entity
public class User
{
    @Id
    @GeneratedValue(strategy = GernrationType.IDENTITY)
    private Long id;
    private String name;
    private String email;

    public Long getID() { return id; }
    public void setID(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
}
