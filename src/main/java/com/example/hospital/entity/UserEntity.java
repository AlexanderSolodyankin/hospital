package com.example.hospital.entity;



import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "users")
public class UserEntity extends BaseEntity implements UserDetails {
    @Column(name = "login")
    private String login;
    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String password;
    @Enumerated(value = EnumType.STRING)
    @Column(name = "active")
    private Status active;
    @Column(name = "code_activities")
    private String codeActivities;
    @Column(name = "date_create")
    private LocalDateTime dateCreate;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "m2m_user_role",
    joinColumns = @JoinColumn(name = "user_id",referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name = "role_id",referencedColumnName = "id"))
    private List<RoleEntity> roleEntityList;

    public UserEntity() {
    }

    public Long getId() {
        return id;
    }

    public BaseEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public String getLogin() {
        return login;
    }

    public UserEntity setLogin(String login) {
        this.login = login;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserEntity setEmail(String email) {
        this.email = email;
        return this;
    }



    public UserEntity setPassword(String password) {
        this.password = password;
        return this;
    }

    public Status getActive() {
        return active;
    }

    public UserEntity setActive(Status active) {
        this.active = active;
        return this;
    }

    public String getCodeActivities() {
        return codeActivities;
    }

    public UserEntity setCodeActivities(String codeActivities) {
        this.codeActivities = codeActivities;
        return this;
    }

    public LocalDateTime getDateCreate() {
        return dateCreate;
    }

    public UserEntity setDateCreate(LocalDateTime dateCreate) {
        this.dateCreate = dateCreate;
        return this;
    }

    public List<RoleEntity> getRoleEntityList() {
        return roleEntityList;
    }

    public UserEntity setRoleEntityList(List<RoleEntity> roleEntityList) {
        this.roleEntityList = roleEntityList;
        return this;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoleEntityList();
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return getLogin();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        if(this.active.equals(Status.BANED)){
            return false;
        }
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        if(!active.equals(Status.ACTIVE)) return false;
        return true;
    }

    @PrePersist
    public void createDateNow(){
        this.dateCreate = LocalDateTime.now();
    }
}
