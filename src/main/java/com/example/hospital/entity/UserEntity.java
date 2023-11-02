package com.example.hospital.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "users")
public class UserEntity  extends BaseEntity{
    @Column(name = "login", unique = true, nullable = false)
    private String login;
    @Column(name = "user_password", nullable = false)
    private String password;
    @Column(name = "email", unique = true, nullable = false)
    private String email;
    @Column(name = "date_create")
    private LocalDateTime dataCreate;

    @Column(name = "active")
    private boolean active;

    public UserEntity() {
    }

    public String getLogin() {
        return login;
    }

    public UserEntity setLogin(String login) {
        this.login = login;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserEntity setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserEntity setEmail(String email) {
        this.email = email;
        return this;
    }

    public LocalDateTime getDataCreate() {
        return dataCreate;
    }

    public UserEntity setDataCreate(LocalDateTime dataCreate) {
        this.dataCreate = dataCreate;
        return this;
    }

    public boolean isActive() {
        return active;
    }

    public UserEntity setActive(boolean active) {
        this.active = active;
        return this;
    }

    @PrePersist
    public void setCreateData(){
        this.dataCreate = LocalDateTime.now();
    }

}
