package com.urlshortenerh2.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@Entity(name = "User")
@Table(name = "users", schema = "urlshortener")
@EqualsAndHashCode(of = "id")

public class User {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username")
    @NotNull(message = "{field.notnull}")
    private String username;

    @Column(name = "email")
    @NotNull(message = "{field.notnull}")
    private String email;

    @Column(name = "password")
    @NotNull(message = "{field.notnull}")
    private String password;

    public User(){

    }

    @Override
    public String toString() {
        return "User{" +
                "  id=" + id +
                ", username='" + username +
                ", email='" + email +
                ", password=" + password +
                '}';
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String name) {
        this.username = name;
    }
}