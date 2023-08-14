package com.example.hospital.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "roles")
public class RoleEntity extends BaseEntity implements GrantedAuthority {

    @Column(name = "role_title")
    private String title;

    @ManyToMany(mappedBy = "roleEntityList",fetch = FetchType.LAZY)
    @JsonIgnore
    private List<UserEntity> userEntityList;


    public RoleEntity() {
    }

    public String getTitle() {
        return title;
    }

    public RoleEntity setTitle(String title) {
        this.title = title;
        return this;
    }

    public List<UserEntity> getUserEntityList() {
        return userEntityList;
    }

    public RoleEntity setUserEntityList(List<UserEntity> userEntityList) {
        this.userEntityList = userEntityList;
        return this;
    }


    @Override
    public String getAuthority() {
        return "ROLE_" + getTitle();
    }
}
